package com.icarodebarros.learnjasperreport.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.repository.PessoaRepository;

import net.sf.jasperreports.engine.JRException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	@Autowired
	private JasperReportService JRService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public List<Pessoa> findAll() {
		return repo.findAll();
	}
	
	public String generateReport(String reportFormat) {
		String result = null;
		try {
			result = this.JRService.exportReport(reportFormat);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Resource getReportAsResource() {
		this.generateReport(this.fileStorageService.getDefaultFileFormat());
		
		return this.fileStorageService.loadDefaultFileAsResource();
	}
	
	public byte[] getReportAsByteArray() {
		this.generateReport(this.fileStorageService.getDefaultFileFormat());
		
		byte[] data = null;
        try {
			data = this.fileStorageService.loadDefaultFileAsByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return data;
	}

}
