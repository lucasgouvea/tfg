package br.edu.unifei.model;

public class Interruptor {

	private long id;
	private boolean ligado;

	public boolean isLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}

	public Interruptor(long id, boolean ligado) {
		super();
		this.id = id;
		this.ligado = ligado;
	}

	public Interruptor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
