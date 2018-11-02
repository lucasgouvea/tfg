package br.edu.unifei.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import br.edu.unifei.model.Viagem;
import br.edu.unifei.repositories.ViagemRepository;


@Service("viagemService")
@Transactional
public class ViagemServiceImpl implements ViagemService{

	@Autowired
	private ViagemRepository viagemRepository;

	@Override
	public Viagem findById(Long id) {
		return viagemRepository.findOne(id);
	}
	

	@Override
	public void saveViagem(Viagem viagem) {
		viagemRepository.save(viagem);
	}

	@Override
	public void updateViagem(Viagem viagem) {
		saveViagem(viagem);
	}

	@Override
	public void deleteViagem(Viagem viagem) {
		viagemRepository.delete(viagem);
	}

	@Override
	public List<Viagem> findAllViagems() {
		return viagemRepository.findAll();
	
	}

	@Override
	public void deleteViagemById(long id) {
	      viagemRepository.delete(id);
		
	}

	@Override
	public void deleteAllViagems() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Viagem findByNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isViagemExist(Viagem Viagem) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
