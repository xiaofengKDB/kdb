package com.soecode.lyf.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.dto.Query;
import com.soecode.lyf.entity.OrderQuery;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
//		ModelAndView mv = new ModelAndView("/orderList");
		return "/orderList.html";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody Object List(Query<OrderQuery> order){
		
		
		return null;
	}
}
