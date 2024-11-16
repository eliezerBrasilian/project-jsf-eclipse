package app.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_tb")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;	
	
	public UserEntity(String name, String email){
		this.name = name;
		this.email = email;
	}
	
	public UserEntity() {}
	
	public Long getId() {
		return id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {	this.name = name;	}

	public String getName() {	return name;}

	public String getEmail() {	return email;}
}
