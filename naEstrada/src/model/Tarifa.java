package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tarifa {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Praca praca;
	
	@ManyToOne
	private Categoria categoria;
	
	private Double valor;
	
	private AplicacaoTarifa aplicacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Praca getPraca() {
		return praca;
	}

	public void setPraca(Praca praca) {
		this.praca = praca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public AplicacaoTarifa getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoTarifa aplicacao) {
		this.aplicacao = aplicacao;
	}
	
}
