package com.project.mysystemproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotBlank
    private String password;

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;
    
    
    private int phone;
    
    @NotBlank
    private String city;
	
    public User(){
        super();
    }
    
    public User(String username, String password, String fname, String lname, int phone, String city) {
        super();
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.city = city;
    }


}
