package com.icarodebarros.learnjasperreport.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.repository.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
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
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
	}

}
