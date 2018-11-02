package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.Viagem;

public interface ViagemService {
	
	Viagem findById(Long id);
	
	Viagem findByNome(String nome);
	
	void saveViagem(Viagem Viagem);
	
	void updateViagem(Viagem Viagem);
	
	void deleteAllViagems();
	
	List<Viagem> findAllViagems();
	
	boolean isViagemExist(Viagem Viagem);

	void deleteViagemById(long id);

	void deleteViagem(Viagem Viagem);
	
}
