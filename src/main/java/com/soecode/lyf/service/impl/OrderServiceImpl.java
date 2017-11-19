package com.soecode.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.soecode.lyf.service.WxOrderService;
import org.springframework.stereotype.Service;

import com.soecode.lyf.common.Query;
import com.soecode.lyf.dao.WxOrderMapper;
import com.soecode.lyf.entity.OrderQuery;
import com.soecode.lyf.entity.WxOrder;
import com.soecode.lyf.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService ,WxOrderService{
	
	@Resource
	private WxOrderMapper wxOrderDao;
	

	@Override
	public List<WxOrder> queryList(Query<OrderQuery> order) {
		List<WxOrder> orderList = wxOrderDao.queryList(order);
		return orderList;
	}


	@Override
	public void insert(WxOrder order) {
		wxOrderDao.insert(order);
	}


	@Override
	public int count(Query<OrderQuery> order) {
		return wxOrderDao.count(order);
	}

	@Override
	public void insertWxOrder(WxOrder wxOrder) {
		wxOrderDao.insert(wxOrder);
	}
}
