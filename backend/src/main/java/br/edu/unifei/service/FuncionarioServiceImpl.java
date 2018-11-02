package br.edu.unifei.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import br.edu.unifei.model.Funcionario;
import br.edu.unifei.repositories.FuncionarioRepository;


@Service("funcionarioService")
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService{

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Funcionario findById(Long id) {
		return funcionarioRepository.findOne(id);
	}
	

	@Override
	public void saveFuncionario(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}

	@Override
	public void updateFuncionario(Funcionario funcionario) {
		saveFuncionario(funcionario);
	}

	@Override
	public void deleteFuncionario(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}

	@Override
	public List<Funcionario> findAllFuncionarios() {
		return funcionarioRepository.findAll();
	
	}

	@Override
	public boolean isFuncionarioExist(Funcionario funcionario) {
		return findByName(funcionario.getName()) != null;
	}

	@Override
	public void deleteFuncionarioById(long id) {
	      funcionarioRepository.delete(id);
		
	}

	@Override
	public void deleteAllFuncionarios() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Funcionario findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
}
