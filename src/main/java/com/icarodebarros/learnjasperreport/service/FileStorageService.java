package com.icarodebarros.learnjasperreport.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.icarodebarros.learnjasperreport.config.FileStorageProperties;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	private final String defaultFileName;
	private final String defaultFileFormat;
	
	private final String getFullDefaultFileName() {
		return this.defaultFileName + "." + this.defaultFileFormat;
	}

	@Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getDownloadDir()).toAbsolutePath().normalize();
        this.defaultFileName = fileStorageProperties.getDefaultFileName();
        this.defaultFileFormat = fileStorageProperties.getDefaultFileFormat();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the created files will be stored.", ex);
        }
    }

    public Resource loadDefaultFileAsResource() {
    	return loadFileAsResource(getFullDefaultFileName());
    }
    
    public Resource loadFileAsResource(String fullFileName) {
        try {
            Path filePath = this.getPath(fullFileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fullFileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File not found " + fullFileName, ex);
        }
    }
    
    public byte[] loadDefaultFileAsByteArray() throws IOException {
    	return this.loadFileAsByteArray(getFullDefaultFileName());
    }
    
    public byte[] loadFileAsByteArray(String fullFileName) throws IOException {
    	this.loadFileAsResource(fullFileName); // Only to test if exists
        Path path = this.getPath(fullFileName);
        return Files.readAllBytes(path);
    }
    
    public Path getDefaultPath() {
		return this.fileStorageLocation.resolve(this.getFullDefaultFileName()).normalize();
	}
    
	public Path getPath(String fullFileName) {
		return this.fileStorageLocation.resolve(fullFileName).normalize();
	}
    
    public String getDefaultFilePathAndName() {
    	return this.getFilePathAndName(this.defaultFileName);
    }
    
    public String getFilePathAndName(String fullFileName) {
    	return this.fileStorageLocation.resolve(fullFileName).normalize().toString();
    }
    	
    public String getDefaultFileFormat() {
		return defaultFileFormat;
	}
    
    public MediaType getMediaTypeForDefaultFileName(ServletContext servletContext) {
    	return FileStorageService.getMediaTypeForFileName(servletContext, this.getFullDefaultFileName());
    }
       
    // abc.zip
    // abc.pdf,..
    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
   
}

