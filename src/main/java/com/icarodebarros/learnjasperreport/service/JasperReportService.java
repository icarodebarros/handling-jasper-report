package com.icarodebarros.learnjasperreport.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.repository.PessoaRepository;

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
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public String exportReport(String reportFormat) throws JRException, IOException {
		// Carrega o template e compila para um JasperReport
		File file = ResourceUtils.getFile("classpath:templates/pessoasTemplate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// Carrega os dados que o template irá mostrar
		List<Pessoa> pessoas = pessoaRepository.findAll();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pessoas);
		
		// Cria o mapa de parâmetros
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Ícaro de Barros");
		// Busca o caminho das imagens presentes no template
		URL imagesUrl = ResourceUtils.getURL("classpath:images");
		parameters.put("imagesPath", imagesUrl.getPath());
		
		// Preenche o jasperReport com os parâmetros e o dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		this.saveReport(jasperPrint, reportFormat);
			
		return "Report gerado em: " + this.fileStorageService.getDefaultFilePathAndName() + "." + reportFormat;
	}
	
	private void saveReport(JasperPrint jasperPrint, String reportFormat) throws JRException, IOException {
//		// Busca a variável de ambiente do windows '%userprofile%'
//		String userprofile = System.getenv("USERPROFILE");
//		
//		if (reportFormat.equalsIgnoreCase("html")) {
//			JasperExportManager.exportReportToHtmlFile(jasperPrint, userprofile + "\\Desktop\\pessoas.html");
//		} else if (reportFormat.equalsIgnoreCase("pdf")) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, userprofile + "\\Desktop\\pessoas.pdf");
//		} else {
//			throw new IOException("Extenção não suportada ou não informada");
//		}
		
		String filePathAndName = this.fileStorageService.getDefaultFilePathAndName();
		
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, filePathAndName + ".html");
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathAndName + ".pdf");
		} else {
			throw new IOException("Extenção não suportada ou não informada");
		}
	}

}
