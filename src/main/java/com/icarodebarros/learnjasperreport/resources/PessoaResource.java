package com.icarodebarros.learnjasperreport.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.service.FileStorageService;
import com.icarodebarros.learnjasperreport.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	private static final Logger logger = LoggerFactory.getLogger(PessoaResource.class);
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private FileStorageService fileStorageService;
	
    @Autowired
    private ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = this.service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/gerarRelatorio/{format}", method = RequestMethod.GET)
	public ResponseEntity<String> generateReport(@PathVariable String format) {
		String result = this.service.generateReport(format);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value="/downloadRelatorio", method = RequestMethod.GET)
	public ResponseEntity<Resource> getReport(HttpServletRequest request) {
		// Load file as Resource
		Resource resource = this.service.getReportAsResource();

		// Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@RequestMapping(value="/downloadRelatorio2", method = RequestMethod.GET)
	public ResponseEntity<Resource> getReport() {
		byte[] data = this.service.getReportAsByteArray();
		ByteArrayResource resource = new ByteArrayResource(data);

		MediaType mediaType = this.fileStorageService.getMediaTypeForDefaultFileName(this.servletContext);
        Path path = this.fileStorageService.getDefaultPath();
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(resource);
	}
 
    // http://localhost:8080/download?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(name = "fileName") String fileName) throws IOException {
 
        MediaType mediaType = FileStorageService.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);
 
        File file = new File(this.fileStorageService.getFilePathAndName(fileName));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

}
