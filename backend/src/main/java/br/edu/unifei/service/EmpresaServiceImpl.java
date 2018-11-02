package br.edu.unifei.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unifei.model.Empresa;
import br.edu.unifei.repositories.EmpresaRepository;

@Service("empresaService")
@Transactional
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepository;



	@Override
	public void saveEmpresa(Empresa empresa) {
		empresaRepository.save(empresa);
	}

	@Override
	public void updateEmpresa(Empresa empresa) {
		saveEmpresa(empresa);
	}

	@Override
	public void deleteEmpresa(Empresa empresa) {
		empresaRepository.delete(empresa);
	}

	@Override
	public List<Empresa> findAllEmpresas() {
		return empresaRepository.findAll();
	
	}

	@Override
	public void deleteEmpresaById(long id) {
		empresaRepository.delete(id);
	}

	@Override
	public Empresa findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllEmpresas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpresaExist(Empresa Empresa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Empresa findById(Long id) {
		return empresaRepository.findOne(id);
	}
	
	
	
}
