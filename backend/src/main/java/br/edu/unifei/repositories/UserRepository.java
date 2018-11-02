package br.edu.unifei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findById(long id);
}
