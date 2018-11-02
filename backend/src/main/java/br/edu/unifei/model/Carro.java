package br.edu.unifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Carro")
public class Carro implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="PreçoDoLitroDeCombustível")
	private float precoDoLitroDeCombustivel;
	
	public float getPrecoDoLitroDeCombustivel() {
		return precoDoLitroDeCombustivel;
	}

	public void setPrecoDoLitroDeCombustivel(float precoDoLitroDeCombustivel) {
		this.precoDoLitroDeCombustivel = precoDoLitroDeCombustivel;
	}

	public long getId() {
		return id;
	}

	public Carro(String nome, float eficiencia) {
		super();
		this.nome = nome;
		this.eficiencia = eficiencia;
	}

	public Carro() {
		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getEficiencia() {
		return eficiencia;
	}

	public void setEficiencia(float eficiencia) {
		this.eficiencia = eficiencia;
	}

	@Column(name="Eficiência")
	private float eficiencia;

}
