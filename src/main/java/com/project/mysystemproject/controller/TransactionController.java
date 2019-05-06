package com.project.mysystemproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mysystemproject.model.Transaction;
import com.project.mysystemproject.repository.CardRepository;
import com.project.mysystemproject.repository.TransactionRepository;

@RestController
public class TransactionController {

	
	@Autowired
	TransactionRepository tranRepository;
	
	@Autowired
	CardRepository cardRepo;
	
	@PostMapping("/payments")
	public boolean addTransaction(@Valid @RequestBody Transaction tran) {
		if (tran.getTamount() > cardRepo.findbalanceBycardno(tran.getCardno())) {
			return false;
		}
		double balance = cardRepo.findbalanceBycardno(tran.getCardno()) - tran.getTamount();
		cardRepo.updateCardbalance(balance,tran.getCardno());
		tranRepository.save(tran);
		return true;
	}
	
	@GetMapping("/transactions")
	@ResponseBody
	public List<Transaction> getTransactions(@RequestParam(value = "username", required = false) String userName) 
	{

		if (userName == null || userName.isEmpty()) {
			return tranRepository.findAll();
		} else {
			return tranRepository.findByusername(userName);
		}
	}
}
