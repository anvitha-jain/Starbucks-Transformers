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
			responseMap.put("result","Successfully added");
			return responseMap;
		}

		responseMap.put("result","Card Number invalid");
		return responseMap;
	}
	
//	@PostMapping("/reloadcards")
//	@ResponseBody
//	public boolean reloadCards(@RequestParam(value = "username") String userName,@RequestParam(value = "balance") double balance,@Valid @RequestBody Card card)
//	{
//		if (card.getUsername() == null || card.getUsername().isEmpty()) {
//			return false;
//		} else {
//			cardRepository.updateReloadCardbalance(card.getUsername(),card.getBalance());
//			return true;
//		}
//	}
//	
	
}
