package com.soecode.lyf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
//		ModelAndView mv = new ModelAndView("/orderList");
		return "/orderList.html";
	}
}
