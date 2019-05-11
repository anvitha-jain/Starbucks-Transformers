package com.project.mysystemproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mysystemproject.model.Card;

/* Card Repository by Amrutha Singh Balaji Singh */

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	
	public List<Card> findByusername(String username);
	
	public List<Card> findBycardno(Long cardno);
	
	@Query("SELECT c.balance FROM Card c WHERE c.cardno = :cardno")
	public double findbalanceBycardno(@Param("cardno") Long cardno);
	
	@Query("SELECT c.cardno FROM Card c WHERE c.username = :username")
	public List<Long> findcardnoByusername(@Param("username") String username);
	
	
    
    @Query(value = "UPDATE cards c set balance =:balance where c.cardno = :cardno",
            nativeQuery = true)
    @Modifying
    @Transactional
    void updateCardbalance(@Param("balance") double balance, @Param("cardno") Long cardno);

	
}
