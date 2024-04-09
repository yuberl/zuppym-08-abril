package com.example.mode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter @Column(name="id")
	private Long id;
	
	@Getter @Setter @Column(name="nombre")
	private String name;
	
	@Getter @Setter @Column(name="apellido")
	private String lastName;
	
	@Getter @Setter @Column(name="email")
	private String mail;
	
	@Getter @Setter @Column(name="telefono")
	private String number;
	
	@Getter @Setter @Column(name="password")
	private String password;

	
}
