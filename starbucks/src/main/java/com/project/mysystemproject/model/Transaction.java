package com.project.mysystemproject.model;

import java.sql.Date;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue
	private Long tid;
	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCardno() {
		return cardno;
	}

	public void setCardno(Long cardno) {
		this.cardno = cardno;
	}

	public Long getTamount() {
		return tamount;
	}

	public void setTamount(Long tamount) {
		this.tamount = tamount;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@NotBlank
	private String username;
	
	
	private Long cardno;
	
	
	private Long tamount;
	
	
	private Date datetime;
	
	
	

}
