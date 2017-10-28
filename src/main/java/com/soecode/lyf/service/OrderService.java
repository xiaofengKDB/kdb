package com.soecode.lyf.service;

import java.util.List;

import com.soecode.lyf.common.Query;
import com.soecode.lyf.entity.OrderQuery;
import com.soecode.lyf.entity.WxOrder;

public interface OrderService {

	List<WxOrder> queryList(Query<OrderQuery> order);
	
	void insert(WxOrder order);

	int count(Query<OrderQuery> order);

}
