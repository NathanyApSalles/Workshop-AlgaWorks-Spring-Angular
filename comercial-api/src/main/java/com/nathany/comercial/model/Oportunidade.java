package com.nathany.comercial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Entity //essa notação indica que a classe é JPA (representa uma tabela do BD)
public class Oportunidade {
	
	@Id // chave primaria do BD
	@GeneratedValue(strategy = GenerationType.IDENTITY) //geração de id do BD (autoincrement)
	private Long id;
	
	@NotEmpty //valida para que o campo não seja nulo
	@Size(max = 80) //valida tamanho do campo
	@Column(name="nome_prospecto")//nome da coluna no BD
	private String nomeProspecto;
	
	@NotEmpty
	@Size(max = 200)
	@Column //aqui não precisa colocar o "name" pq já tem o mesmo nome da coluna do BD
	private String descricao;
	
	@Min(0)
	@Column
	private BigDecimal valor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeProspecto() {
		return nomeProspecto;
	}
	public void setNomeProspecto(String nomeProspecto) {
		this.nomeProspecto = nomeProspecto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
