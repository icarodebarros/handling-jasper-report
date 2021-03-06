package com.icarodebarros.learnjasperreport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icarodebarros.learnjasperreport.domain.Desempenho;
import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.repository.DesempenhoRepository;
import com.icarodebarros.learnjasperreport.repository.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DesempenhoRepository desempenhoRepository;
	
	public void instantiateTestDatabase() throws ParseException {

		Pessoa p1 = new Pessoa(null, "Joao da Silva", "M", "52653299003", "joao.silva@email.com");
		Pessoa p2 = new Pessoa(null, "Maria do Carmo", "F", "10374592020", "maria.carmo@email.com");
		Pessoa p3 = new Pessoa(null, "Joaquim Amaral", "M", "14224842033", "Joaquim.amaral@email.com");
		Pessoa p4 = new Pessoa(null, "Alquingel dos Santos", "M", "03741128007", "santos321@email.com");
		Pessoa p5 = new Pessoa(null, "Tatiane Albuquerque", "F", "04466618054", "tati.albuquerque@email.com");
		Pessoa p6 = new Pessoa(null, "John Smith", "M", "71921418010", "smith.john@email.com");
		Pessoa p7 = new Pessoa(null, "Estrela Cadente", "F", "59348328085", "star@email.com");
		Pessoa p8 = new Pessoa(null, "Aderbal Soares", "M", "79641106082", "aderbal.soares@email.com");
		Pessoa p9 = new Pessoa(null, "Filipe Figueiredo", "M", "77613310060", "fil.fig@email.com");
		Pessoa p10 = new Pessoa(null, "Gabriela Tobias", "F", "22129245005", "gaby.tobias@email.com");
		
		Pessoa p11 = new Pessoa(null, "Joao da Silva", "M", "52653299003", "joao.silva@email.com");
		Pessoa p12 = new Pessoa(null, "Maria do Carmo", "F", "10374592020", "maria.carmo@email.com");
		Pessoa p13 = new Pessoa(null, "Joaquim Amaral", "M", "14224842033", "Joaquim.amaral@email.com");
		Pessoa p14 = new Pessoa(null, "Alquingel dos Santos", "M", "03741128007", "santos321@email.com");
		Pessoa p15 = new Pessoa(null, "Tatiane Albuquerque", "F", "04466618054", "tati.albuquerque@email.com");
		Pessoa p16 = new Pessoa(null, "John Smith", "M", "71921418010", "smith.john@email.com");
		Pessoa p17 = new Pessoa(null, "Estrela Cadente", "F", "59348328085", "star@email.com");
		Pessoa p18 = new Pessoa(null, "Aderbal Soares", "M", "79641106082", "aderbal.soares@email.com");
		Pessoa p19 = new Pessoa(null, "Filipe Figueiredo", "M", "77613310060", "fil.fig@email.com");
		Pessoa p20 = new Pessoa(null, "Gabriela Tobias", "F", "22129245005", "gaby.tobias@email.com");
		
		Pessoa p21 = new Pessoa(null, "Joao da Silva", "M", "52653299003", "joao.silva@email.com");
		Pessoa p22 = new Pessoa(null, "Maria do Carmo", "F", "10374592020", "maria.carmo@email.com");
		Pessoa p23 = new Pessoa(null, "Joaquim Amaral", "M", "14224842033", "Joaquim.amaral@email.com");
		Pessoa p24 = new Pessoa(null, "Alquingel dos Santos", "M", "03741128007", "santos321@email.com");
		Pessoa p25 = new Pessoa(null, "Tatiane Albuquerque", "F", "04466618054", "tati.albuquerque@email.com");
		Pessoa p26 = new Pessoa(null, "John Smith", "M", "71921418010", "smith.john@email.com");
		Pessoa p27 = new Pessoa(null, "Estrela Cadente", "F", "59348328085", "star@email.com");
		Pessoa p28 = new Pessoa(null, "Aderbal Soares", "M", "79641106082", "aderbal.soares@email.com");
		Pessoa p29 = new Pessoa(null, "Filipe Figueiredo", "M", "77613310060", "fil.fig@email.com");
		Pessoa p30 = new Pessoa(null, "Gabriela Tobias", "F", "22129245005", "gaby.tobias@email.com");
		
		Pessoa p31 = new Pessoa(null, "Joao da Silva", "M", "52653299003", "joao.silva@email.com");
		Pessoa p32 = new Pessoa(null, "Maria do Carmo", "F", "10374592020", "maria.carmo@email.com");
		Pessoa p33 = new Pessoa(null, "Joaquim Amaral", "M", "14224842033", "Joaquim.amaral@email.com");
		Pessoa p34 = new Pessoa(null, "Alquingel dos Santos", "M", "03741128007", "santos321@email.com");
		Pessoa p35 = new Pessoa(null, "Tatiane Albuquerque", "F", "04466618054", "tati.albuquerque@email.com");
		Pessoa p36 = new Pessoa(null, "John Smith", "M", "71921418010", "smith.john@email.com");
		Pessoa p37 = new Pessoa(null, "Estrela Cadente", "F", "59348328085", "star@email.com");
		Pessoa p38 = new Pessoa(null, "Aderbal Soares", "M", "79641106082", "aderbal.soares@email.com");
		Pessoa p39 = new Pessoa(null, "Filipe Figueiredo", "M", "77613310060", "fil.fig@email.com");
		Pessoa p40 = new Pessoa(null, "Gabriela Tobias", "F", "22129245005", "gaby.tobias@email.com");
		  
		Desempenho d1 = new Desempenho(null, new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2020"), 50, p1);
		Desempenho d2 = new Desempenho(null, new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2020"), 52, p1);
		Desempenho d3 = new Desempenho(null, new SimpleDateFormat("dd/MM/yyyy").parse("25/03/2020"), 60, p1);
		Desempenho d4 = new Desempenho(null, new SimpleDateFormat("dd/MM/yyyy").parse("10/04/2020"), 80, p1);
		Desempenho d5 = new Desempenho(null, new SimpleDateFormat("dd/MM/yyyy").parse("30/04/2020"), 75, p1);
		
		p1.getDesempenhos().addAll(Arrays.asList(d1, d2, d3, d4, d5));
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
		pessoaRepository.saveAll(Arrays.asList(p11, p12, p13, p14, p15, p16, p17, p18, p19, p20));
		pessoaRepository.saveAll(Arrays.asList(p21, p22, p23, p24, p25, p26, p27, p28, p29, p30));
		pessoaRepository.saveAll(Arrays.asList(p31, p32, p33, p34, p35, p36, p37, p38, p39, p40));
		desempenhoRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5));
	}

}
