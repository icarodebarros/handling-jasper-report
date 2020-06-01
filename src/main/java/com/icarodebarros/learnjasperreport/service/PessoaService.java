package com.icarodebarros.learnjasperreport.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.icarodebarros.learnjasperreport.domain.Desempenho;
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
	
	public Pessoa find(Integer id) {
		Pessoa pessoa =  repo.findById(id).orElseThrow(() -> new RuntimeException("Objeto não encontrado!" + Pessoa.class));
		if (pessoa.getDesempenhos() != null) {
			for (Desempenho desempenho: pessoa.getDesempenhos()) {
				desempenho.setPessoa(null);
			}
		}
		return pessoa;
	}
	
	public List<Pessoa> findAll() {
		return repo.findAll();
	}
	
	public String generateReport(String fileName) {
		String result = null;
		try {
			result = this.JRService.exportReport(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Resource getReportAsResource(String fileName) {
		this.generateReport(fileName);
		
		return this.fileStorageService.loadFileAsResource(fileName);
	}
	
	public byte[] getReportAsByteArray(String fileName) {
		this.generateReport(fileName);
		
		byte[] data = null;
        try {
			data = this.fileStorageService.loadFileAsByteArray(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return data;
	}
	
	public File getReportAsFile(String fileName) {
		this.generateReport(fileName);
		
		return new File(this.fileStorageService.getPath(fileName).toString());
	}
	
	// ----------------------------------------------------------
	
	public String generateChartReport(Integer pessoaId, String fileName) {
		String result = null;
		try {
			result = this.JRService.exportChartReport(pessoaId, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// ----------------------------------------------------------
	
	public String generateFullReport(Integer pessoaId, String fileName) {
		String result = null;
		try {
			result = this.JRService.exportFullReport(pessoaId, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return result;
	}
}
