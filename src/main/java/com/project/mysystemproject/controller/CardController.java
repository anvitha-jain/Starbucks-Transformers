package com.project.mysystemproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.mysystemproject.model.Card;
import com.project.mysystemproject.repository.CardRepository;


import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CardController {

	@Autowired
	CardRepository cardRepository;
	

	@GetMapping("/cards")
	@ResponseBody
	public List<Card> getCards(@RequestParam(value = "username", required = false) String userName) 
	{

		if (userName == null || userName.isEmpty()) {
			return cardRepository.findAll();
		} else {
			return cardRepository.findByusername(userName);
		}
	}
	
	@PostMapping("/cards")
	public Map<String, String> addCard(@Valid @RequestBody Card card) 
	{
		Map<String, String> responseMap = new HashMap<String,String>();
		List<Card> listOfAddedCards = cardRepository.findBycardno(card.getCardno());
		if (listOfAddedCards.size() == 0) // Its a new card
		{
			cardRepository.save(card);
			responseMap.put("result","true");
			return responseMap;
		}
		else
		{
			responseMap.put("result","false");
			return responseMap;
		}

	}
	
	@PostMapping("/reloadcards")
	@ResponseBody
	public boolean reloadCards(@RequestParam(value = "cardno") Long cardno,@RequestParam(value = "balance") double balance, @Valid @RequestBody Card card)
	{

		List<Card> cards = cardRepository.findBycardno(cardno);
		if(cards.size() == 1) {
			double updated_balance = card.getBalance() + balance;
			card.setBalance(updated_balance);
			cardRepository.updateCardbalance(updated_balance, card.getCardno());
			return true;
		} else {
			return false;
		}
	}	
	
}
