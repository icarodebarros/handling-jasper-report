package com.icarodebarros.learnjasperreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.icarodebarros.learnjasperreport.domain.Desempenho;
import com.icarodebarros.learnjasperreport.domain.Pessoa;

public interface DesempenhoRepository extends JpaRepository<Desempenho, Integer> {
	
	@Transactional(readOnly = true)
	List<Desempenho> findByPessoa(Pessoa pessoa);

}
