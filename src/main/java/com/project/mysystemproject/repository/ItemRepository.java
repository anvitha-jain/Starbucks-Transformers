package com.project.mysystemproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mysystemproject.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	
	
	@Query("SELECT c.item_price FROM Item c WHERE c.item_name = :item_name")
	public double finditem_priceByitem_name(@Param("item_name") String item_name);
	
	@Query("SELECT c.item_qty FROM Item c WHERE c.item_name = :item_name")
	public double finditem_qtyByitem_name(@Param("item_name") String item_name);
}
