package br.edu.unifei.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import br.edu.unifei.model.Carro;
import br.edu.unifei.repositories.CarroRepository;


@Service("carroService")
@Transactional
public class CarroServiceImpl implements CarroService{

	@Autowired
	private CarroRepository carroRepository;

	@Override
	public Carro findById(Long id) {
		return carroRepository.findOne(id);
	}
	@Override
	public void saveCarro(Carro carro) {
		carroRepository.save(carro);
	}

	@Override
	public void updateCarro(Carro carro) {
		Carro carroFromDb = carroRepository.findOne(carro.getId());
		if(carroFromDb != null) {
			carroFromDb = carro;
		}
		carroRepository.save(carroFromDb);

	}

	@Override
	public void deleteCarro(Carro carro) {
		carroRepository.delete(carro);
	}

	@Override
	public List<Carro> findAllCarros() {
		return carroRepository.findAll();
	
	}

	@Override
	public boolean isCarroExist(Carro carro) {
		return findByNome(carro.getNome()) != null;
	}

	@Override
	public void deleteCarroById(long id) {
	      carroRepository.delete(id);
		
	}

	@Override
	public void deleteAllCarros() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Carro findByNome(String nome) {
		carroRepository.findByNome(nome);
		return null;
	}

}
