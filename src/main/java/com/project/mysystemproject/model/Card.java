package com.project.mysystemproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cards")
public class Card {
	
	@Id
	private Long cardno;
	
	public Long getCardno() {
		return cardno;
	}


	public void setCardno(Long cardno) {
		this.cardno = cardno;
	}


	public int getCardexpiry() {
		return cardexpiry;
	}


	public void setCardexpiry(int cardexpiry) {
		this.cardexpiry = cardexpiry;
	}


	public String getCardholdername() {
		return cardholdername;
	}


	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}


	public int getCvv() {
		return cvv;
	}


	public void setCvv(int cvv) {
		this.cvv = cvv;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	public Card() {
		super();
	}
	
	public Card(Long cardno, int cardexpiry, String cardholdername, int cvv, String username, double balance) {
        super();
        this.cardno = cardno;
        this.cardexpiry = cardexpiry;
        this.cvv = cvv;
        this.username = username;
        this.balance = balance;
     
    }


	
	private int cardexpiry;
	
	@NotBlank
	private String cardholdername;
	
	
	private int cvv;
	
	@NotBlank
	private String username;
	
	
	private double balance;

}
