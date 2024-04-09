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
@Table(name = "pymesUsers")
public class PyUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter @Column(name="pymesId")
	private Long pyId;
	
	@Getter @Setter @Column(name="pymesName")
	private String pyName;
	
	@Getter @Setter @Column(name="pymesNit")
	private String pyNit;
	
	@Getter @Setter @Column(name="pymesNumber")
	private String pyNumber;
	
	@Getter @Setter @Column(name="pymesPassword")
	private String pyPassword;

	
}