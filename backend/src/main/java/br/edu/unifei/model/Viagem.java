package br.edu.unifei.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Viagem")
public class Viagem implements Serializable{
	
	public Viagem() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Horário", nullable = false)
	private String horario;
	
	@Column(name="KmRodados", nullable = false)
	private float kmRodados;
	
	@Column(name="Receita",nullable=false)
	private float receita;

	@Column(name="Lucro",nullable=false)
	private float lucro;
	
	@Column(name="Data",nullable=false)
	private Date data;
	
	@Column(name="Funcionario",nullable=false)
	private Funcionario funcionario;
	
	@Column(name="Carro",nullable=false)
	private Carro carro;
	
	@Column(name="Empresa",nullable=false)
	private Empresa empresa;
	public String getHorario() {
		return horario;
	}
	
	public void setReceita(float receita) {
		this.receita = receita;
		if(this.carro != null) {
			this.lucro = this.receita - ((this.kmRodados) / (this.getCarro().getEficiencia())) * this.getCarro().getPrecoDoLitroDeCombustivel();			
		}

	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
		if(this.receita > 0) {
			this.lucro = this.receita - ((this.kmRodados) / (this.getCarro().getEficiencia())) * this.getCarro().getPrecoDoLitroDeCombustivel();			
		}
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public float getKmRodados() {
		return kmRodados;
	}

	public void setKmRodados(float kmRodados) {
		this.kmRodados = kmRodados;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getReceita() {
		return receita;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Carro getCarro() {
		return carro;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getLucro() {
		return lucro;
	}

	public void setLucro(float lucro) {
		this.lucro = lucro;
	}


}
