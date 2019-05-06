package com.project.mysystemproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mysystemproject.model.Transaction;
import com.project.mysystemproject.model.Card;
import com.project.mysystemproject.repository.CardRepository;
import com.project.mysystemproject.repository.TransactionRepository;

@RestController
public class TransactionController {

	
	@Autowired
	TransactionRepository tranRepository;
	@Autowired
	CardRepository cardRepo;
	
	@PostMapping("/payment")
	public boolean addTransaction(@Valid @RequestBody Transaction tran,@Valid @RequestBody Card card) 
	{
		if(tran.getTamount() > cardRepo.findbalanceBycardno(card.getCardno()))
				{
					return false;
				}
		
		tranRepository.save(tran);
		return true;
	}
}
