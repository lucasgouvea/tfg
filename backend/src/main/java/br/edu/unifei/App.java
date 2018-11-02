package br.edu.unifei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//import br.edu.unifei.configuration.JpaConfiguration;

//@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages= {"br.edu.unifei"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
