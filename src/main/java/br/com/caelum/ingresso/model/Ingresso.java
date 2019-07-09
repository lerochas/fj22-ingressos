package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Hibernate;

import br.com.caelum.ingresso.model.desconto.Desconto;

@Entity
public class Ingresso {
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private	Sessao	sessao;
	
	private	BigDecimal	preco;
	
	@ManyToOne
	private Lugar lugar;
	
	@Enumerated(EnumType.STRING)
	private TipoDeIngresso tipoDeIngresso;
	
	/**
	 * @deprecated hibernate only
	 */
	public Ingresso() {	}
	
	public	Ingresso(Sessao	sessao, Lugar lugar, TipoDeIngresso tipoDeIngresso) {
		this.setSessao(sessao);
		this.preco	= tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar;
		this.tipoDeIngresso = tipoDeIngresso;
	}
	
	public Lugar getLugar() {
		return lugar;
	}

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}
	
	public	BigDecimal	getPreco() {
		return	preco.setScale(2,	RoundingMode.HALF_UP);
	}
	
	public	void	setPreco(BigDecimal	preco) {
		this.preco	=	preco;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
}
