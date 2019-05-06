package com.project.mysystemproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mysystemproject.model.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	
	public List<Card> findByusername(String username);
	
	public List<Card> findBycardno(Long cardno);
	
	@Query("SELECT c.balance FROM Card c WHERE c.cardno = :cardno")
	public int findbalanceBycardno(@Param("cardno") Long cardno);
	
	//@Modifying
	
}