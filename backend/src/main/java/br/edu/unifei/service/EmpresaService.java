package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.Empresa;

public interface EmpresaService {
	
	Empresa findById(Long id);
	
	Empresa findByName(String name);
	
	void saveEmpresa(Empresa Empresa);
	
	void updateEmpresa(Empresa Empresa);
	
	void deleteAllEmpresas();
	
	List<Empresa> findAllEmpresas();
	
	boolean isEmpresaExist(Empresa Empresa);

	void deleteEmpresaById(long id);

	void deleteEmpresa(Empresa Empresa);
	
}
