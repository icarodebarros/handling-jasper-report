package com.icarodebarros.learnjasperreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.icarodebarros.learnjasperreport.domain.Desempenho;
import com.icarodebarros.learnjasperreport.domain.Pessoa;
import com.icarodebarros.learnjasperreport.repository.DesempenhoRepository;

public class DesempenhoService {
	
	@Autowired
	private DesempenhoRepository repo;
	
	public List<Desempenho> findByPessoa(Pessoa pessoa) {
		return this.repo.findByPessoa(pessoa);
	}

}
