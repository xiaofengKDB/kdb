package com.soecode.lyf.web;


import java.util.List;

import javax.annotation.Resource;

import com.soecode.lyf.entity.WxOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.common.PageBase;
import com.soecode.lyf.common.Query;
import com.soecode.lyf.entity.OrderQuery;
import com.soecode.lyf.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
		return "/orderList.html";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody Object List(Query<OrderQuery> order){
		if(order.getData() == null){
			OrderQuery orderQuery = new OrderQuery();
			order.setData(orderQuery);
		}
		PageBase<WxOrder> page = new PageBase<WxOrder>();
		List<WxOrder> orderList = orderService.queryList(order);
		int count = orderService.count(order);
		page.setResult(orderList);
		page.setTotal((long)count);
//		new PageResultForBootstrap();
		return page;
	}
}
