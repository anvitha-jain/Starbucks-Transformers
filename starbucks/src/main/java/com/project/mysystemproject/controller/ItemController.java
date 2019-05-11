package com.project.mysystemproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mysystemproject.model.Item;
import com.project.mysystemproject.model.Transaction;
import com.project.mysystemproject.repository.ItemRepository;

/** Created by Poorva Agarwal **/
@RestController
public class ItemController {

	
	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping("/item1")
	@ResponseBody
	public Map<String, String> getTotalPriceCoffee(@RequestParam(value = "item_qty") Double item_qty, 
			@RequestParam(value = "item_name") String item_name,
			@Valid @RequestBody Item item) 
	{
		
		//double q2 = Double.parseDouble(item_qty);
		
		Map<String, String> responseMap = new HashMap<String,String>();
      if(item_qty > itemRepository.finditem_qtyByitem_name(item_name))
      {
    	  responseMap.put("result","false");
    	  responseMap.put("value","coffee");
    	  
			return responseMap;
      }
      else
      {
    	  double price = itemRepository.finditem_priceByitem_name(item_name);
    	  double amount = price * item_qty;
    	  String order_value= Double.toString(amount);
    	  responseMap.put("result","true");
    	  responseMap.put("value",order_value);
    	  return responseMap;
      }
       
	}
	
	@GetMapping("/item2")
	@ResponseBody
	public Map<String, String> getTotalPriceExpresso(@RequestParam(value = "item_qty") Double item_qty, 
			@RequestParam(value = "item_name") String item_name,
			@Valid @RequestBody Item item) 
	{
		
		//double q2 = Double.parseDouble(item_qty);
		
		Map<String, String> responseMap = new HashMap<String,String>();
      if(item_qty > itemRepository.finditem_qtyByitem_name(item_name))
      {
    	  responseMap.put("result","false");
    	  responseMap.put("value","expresso");
    	  
			return responseMap;
      }
      else
      {
    	  double price = itemRepository.finditem_priceByitem_name(item_name);
    	  double amount = price * item_qty;
    	  String order_value= Double.toString(amount);
    	  responseMap.put("result","true");
    	  responseMap.put("value",order_value);
    	  return responseMap;
      }
       
	}
	
}
