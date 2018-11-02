package br.edu.unifei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	Empresa findByNome(String nome);

}
