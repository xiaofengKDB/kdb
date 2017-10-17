package com.soecode.lyf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping(value="/view",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView view(){
		ModelAndView mv = new ModelAndView("/orderList");
		return mv;
	}
}
