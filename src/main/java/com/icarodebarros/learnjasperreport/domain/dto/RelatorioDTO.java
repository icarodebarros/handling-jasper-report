package com.icarodebarros.learnjasperreport.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.icarodebarros.learnjasperreport.domain.Pessoa;

public class RelatorioDTO {
	
	private Pessoa destaque;
			
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public RelatorioDTO() {}
	
	public RelatorioDTO(Pessoa destaque, List<Pessoa> pessoas) {
		this.destaque = destaque;
		this.pessoas = pessoas;
	}

	public Pessoa getDestaque() {
		return destaque;
	}

	public void setDestaque(Pessoa destaque) {
		this.destaque = destaque;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
