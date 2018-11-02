package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.Funcionario;

public interface FuncionarioService {
	
	Funcionario findById(Long id);
	
	Funcionario findByName(String name);
	
	void saveFuncionario(Funcionario Funcionario);
	
	void updateFuncionario(Funcionario Funcionario);
	
	void deleteAllFuncionarios();
	
	List<Funcionario> findAllFuncionarios();
	
	boolean isFuncionarioExist(Funcionario Funcionario);

	void deleteFuncionarioById(long id);

	void deleteFuncionario(Funcionario Funcionario);
	
}
