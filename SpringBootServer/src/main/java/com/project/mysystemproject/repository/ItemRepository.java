package com.project.mysystemproject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	
	/** Created by Poorva Agarwal 
    The below query updates the qty of stock after payment
   */
   @Query(value = "UPDATE items c set item_qty =:item_qty where c.item_name = :item_name",
           nativeQuery = true)
   @Modifying
   @Transactional
   void updateItemqty(@Param("item_qty") double item_qty, @Param("item_name") String item_name);
}
