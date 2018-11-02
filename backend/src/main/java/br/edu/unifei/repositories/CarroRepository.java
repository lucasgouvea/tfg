package br.edu.unifei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.unifei.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	Carro findByNome(String nome);
	@Modifying()
	@Query("update Carro c set c.nome = ?1 where c.id = ?2")
	void setNomeById(String nome, long id);

}
