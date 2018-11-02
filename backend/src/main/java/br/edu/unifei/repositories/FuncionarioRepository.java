package br.edu.unifei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Funcionario findByNome(String nome);

}
