package com.icarodebarros.learnjasperreport.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.domain.dto.RelatorioDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportService {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	private void saveReport(JasperPrint jasperPrint, String fileName) throws JRException, IOException {
//		// Busca a variável de ambiente do windows '%userprofile%'
//		String userprofile = System.getenv("USERPROFILE");
//		String filePathAndName = userprofile + "\\Desktop\\" + fileName;
		
		String filePathAndName = this.fileStorageService.getPath(fileName).toString();
		String reportFormat = fileName.split("\\.")[1];
		
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, filePathAndName);
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathAndName);
		} else {
			throw new IOException("Extenção não suportada ou não informada");
		}
	}
	
	public String exportReport(String fileName) throws JRException, IOException {
		// Carrega o template e compila para um JasperReport
		File file = ResourceUtils.getFile("classpath:templates/pessoasTemplate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// Carrega os dados que o template irá mostrar
		List<Pessoa> pessoas = pessoaService.findAll();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pessoas);
		
		// Cria o mapa de parâmetros
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Ícaro de Barros");
		// Busca o caminho das imagens presentes no template
		URL imagesUrl = ResourceUtils.getURL("classpath:images");
		parameters.put("imagesPath", imagesUrl.getPath());
		
		// Preenche o jasperReport com os parâmetros e o dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		this.saveReport(jasperPrint, fileName);
			
		return "Report gerado em: " + this.fileStorageService.getPath(fileName).toString();
	}
	
	public String exportChartReport(Integer pessoaId, String fileName) throws JRException, IOException {
		// Carrega o template e compila para um JasperReport
		File file = ResourceUtils.getFile("classpath:templates/desempenhoTemplate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// Carrega os dados que o template irá mostrar
		Pessoa pessoa = pessoaService.find(pessoaId);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(pessoa));
		
		// Cria o mapa de parâmetros
		Map<String, Object> parameters = new HashMap<>();
		
		// Preenche o jasperReport com os parâmetros e o dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		this.saveReport(jasperPrint, fileName);
			
		return "Report gerado em: " + this.fileStorageService.getPath(fileName).toString();
	}
	
	public String exportFullReport(Integer pessoaId, String fileName) throws JRException, IOException {
		// Compila as páginas que compõe o report
		File pessoasTemplate = ResourceUtils.getFile("classpath:templates/pessoasTemplate.jrxml");
		JasperReport pessoasJR = JasperCompileManager.compileReport(pessoasTemplate.getAbsolutePath());
		File desempenhoTemplate = ResourceUtils.getFile("classpath:templates/desempenhoTemplate.jrxml");
		JasperReport desempenhoJR = JasperCompileManager.compileReport(desempenhoTemplate.getAbsolutePath());
		
		// Carrega o template e compila para um JasperReport
		File file = ResourceUtils.getFile("classpath:templates/relatorio.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// Carrega os dados que o template irá mostrar
		Pessoa pessoaDestaque = pessoaService.find(pessoaId);
		List<Pessoa> pessoas = pessoaService.findAll();
		RelatorioDTO relatorioDto = new RelatorioDTO(pessoaDestaque, pessoas);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(relatorioDto));
		
		// Cria o mapa de parâmetros
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Ícaro de Barros");
		// Busca o caminho das imagens presentes no template
		URL imagesUrl = ResourceUtils.getURL("classpath:images");
		parameters.put("imagesPath", imagesUrl.getPath());
		// Coloca como parâmetros os templates compilados de compõe o report
		parameters.put("pessoasJR", pessoasJR);
		parameters.put("desempenhoJR", desempenhoJR);
		
		// Preenche o jasperReport com os parâmetros e o dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		this.saveReport(jasperPrint, fileName);
			
		return "Report gerado em: " + this.fileStorageService.getPath(fileName).toString();
	}

}
