package com.project.mysystemproject.model;



import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;


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

	public double getTamount() {
		return tamount;
	}

	public void setTamount(double tamount) {
		this.tamount = tamount;
	}
	
	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	@NotBlank
	private String username;
	
	private Long cardno;
	
	private double tamount;
	
	@CreationTimestamp
	private LocalDateTime datetime;
}
