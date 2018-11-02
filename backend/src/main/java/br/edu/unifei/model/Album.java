package br.edu.unifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
						

			
@SuppressWarnings("serial")
@Entity
@Table(name="ALBUM")
public class Album implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;
	
	/* A ser implementado
	@OneToMany
	private List<Screenshot> screenshots = new ArrayList<>();
	*/
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	/*

	public Album(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
*/

	
	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + "]";
	}
	
	

}
