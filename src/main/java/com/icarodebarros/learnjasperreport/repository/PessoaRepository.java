package com.icarodebarros.learnjasperreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icarodebarros.learnjasperreport.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
