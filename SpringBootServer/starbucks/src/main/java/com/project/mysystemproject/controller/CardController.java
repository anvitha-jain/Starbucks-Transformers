package com.project.mysystemproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.mysystemproject.exception.CardNotFoundException;
import com.project.mysystemproject.model.Card;
import com.project.mysystemproject.repository.CardRepository;
import com.project.mysystemproject.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

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
	public boolean addCard(@Valid @RequestBody Card card) 
	{
		List<Card> listOfAddedCards = cardRepository.findBycardno(card.getCardno());
		if (listOfAddedCards.size() == 0) // Its a new card
		{
			cardRepository.save(card);
			return true;
		}

		return false;
	}
}
