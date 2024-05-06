package com.example.program.entity;

 
import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="First_Name")
	private String firstName;
		
	@Column(name="Last_Name")
	private String LastName;
	
	@Column(name="email")
	private String  email;
	

	public Student() {
		
	}


	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
