package com.project.mysystemproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
public class Item {
    
	@Id
	@GeneratedValue
	private Long item_id;
	
	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getItem_qty() {
		return item_qty;
	}

	public void setItem_qty(double item_qty) {
		this.item_qty = item_qty;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	
	private String item_name;
	
	private double item_qty;
	
	private double item_price;
	
	
}
