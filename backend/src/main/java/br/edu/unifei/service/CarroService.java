package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.Carro;

public interface CarroService {
	
	Carro findById(Long id);
	
	Carro findByNome(String nome);
	
	void saveCarro(Carro Carro);
	
	void updateCarro(Carro Carro);
	
	void deleteAllCarros();
	
	List<Carro> findAllCarros();
	
	boolean isCarroExist(Carro Carro);

	void deleteCarroById(long id);

	void deleteCarro(Carro Carro);

	
}
