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
	
	@RequestMapping(value="/gerarRelatorio/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<String> generateReport(@PathVariable String fileName) {
		String result = this.service.generateReport(fileName);
		return ResponseEntity.ok().body(result);
	}
	
	// Download as Resource
	@RequestMapping(value="/resourceDownload", method = RequestMethod.GET)
	public ResponseEntity<Resource> getReport(HttpServletRequest request) {		
		String fileName = "RELATORIO.PDF";
		
		// Load file as Resource
		Resource resource = this.service.getReportAsResource(fileName);

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
	
	// Download as ByteArrayResource
	@RequestMapping(value="/byteArrayDownload", method = RequestMethod.GET)
	public ResponseEntity<Resource> getReport() {
		String fileName = "RELATORIO.PDF";
		byte[] data = this.service.getReportAsByteArray(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);

		MediaType mediaType = FileStorageService.getMediaTypeForFileName(this.servletContext, fileName);
        Path path = this.fileStorageService.getPath(fileName);
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(resource);
	}
 
    // http://localhost:8080/download?fileName=abc.zip
    // Download as InputStreamResource
    @RequestMapping("/inputStreamDownload")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(name = "fileName") String fileName) throws IOException {
 
        MediaType mediaType = FileStorageService.getMediaTypeForFileName(this.servletContext, fileName);
 
        File file = this.service.getReportAsFile(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

}
