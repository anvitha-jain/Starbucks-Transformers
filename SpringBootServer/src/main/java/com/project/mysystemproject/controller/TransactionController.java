package com.project.mysystemproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.project.mysystemproject.repository.ItemRepository;
import com.project.mysystemproject.repository.TransactionRepository;

/** Created by Poorva Agarwal **/
@RestController
public class TransactionController {

	
	@Autowired
	TransactionRepository tranRepository;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	ItemRepository itemRepo;
	

//	@GetMapping("/orders")
//	@ResponseBody
//	public int calculateAmount(@RequestParam(value = "item1_qty")String item1_qty,@RequestParam(value = "item2_qty") String item2_qty)
//	{
//		int item1_price = 3;
//		int item2_price = 2;
//		int q1 = Integer.parseInt(item1_qty);
//		int q2 = Integer.parseInt(item2_qty);
//		int total_amount = (q1 * item1_price) + (q2 * item1_price);
//		return total_amount;
//		
//	}
	
	
	@PostMapping("/payments")
	public Map<String, String> addTransaction(@Valid @RequestBody Transaction tran) {
		
		Map<String, String> responseMap = new HashMap<String,String>();
		if (tran.getTamount() > cardRepo.findbalanceBycardno(tran.getCardno())) {
			responseMap.put("result","false");
			return responseMap;
		}
		double balance = cardRepo.findbalanceBycardno(tran.getCardno()) - tran.getTamount();
		cardRepo.updateCardbalance(balance,tran.getCardno());
		String item_name1 = "coffee";
		String item_name2 = "expresso";
		Double stock1 = itemRepo.finditem_qtyByitem_name(item_name1);
		Double stock2 = itemRepo.finditem_qtyByitem_name(item_name2);
		Double updated_qty1 = stock1 - tran.getQty1();
		Double updated_qty2 = stock2 - tran.getQty2();
		itemRepo.updateItemqty(updated_qty1,item_name1);
		itemRepo.updateItemqty(updated_qty2,item_name2);
		tranRepository.save(tran);
		responseMap.put("result","true");
		return responseMap;
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
