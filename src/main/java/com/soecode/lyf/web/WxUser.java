package com.soecode.lyf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx")
public class WxUser {
	
	@RequestMapping("/sendExpView")
	public void sendExpressView(HttpServletRequest rq,HttpServletResponse rp){
		
	}

}
