package com.kdb.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.soecode.lyf.common.Query;
import com.soecode.lyf.entity.OrderQuery;
import com.soecode.lyf.service.OrderService;

public class OrderTest extends JunitBaseTest{
	
	@Resource
	private OrderService orderService;
	
	@Test
	public void ordertest(){
		WxOrder order = new WxOrder();
		order.setOrderid("2");
		order.setWxopenid("0000123");
		orderService.insert(order);
	}
	
	@Test
	public void orderList(){
		Query<OrderQuery> order = new Query<OrderQuery>();
		OrderQuery orderQuery = new OrderQuery();
		order.setData(orderQuery);
//		OrderQuery data = order.getData();
//		data.setOrderId("WX000001");
		List<WxOrder> queryList = new ArrayList<WxOrder>();
		try {
			queryList = orderService.queryList(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(queryList.toString());
		
	}
}
