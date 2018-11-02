package br.edu.unifei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.model.Screenshot;

public interface ScreenshotRepository extends JpaRepository<Screenshot, Long>{

	Screenshot findById(long id);
	
}
