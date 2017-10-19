package com.soecode.lyf.dto;

import java.io.Serializable;

public class Query<T> extends QueryBase implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 4506630318778571348L;
	/**
     * 属性
     * 方法  属性
     *
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     *
     *
     */
    private T data;

    /**
     * orderBy
     * 方法  属性
     *
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     *
     *
     */
    private String orderBy;

    /**
     * getData
     *
     * @return
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     */
    public T getData() {
        return data;
    }

    /**
     * setData
     *
     * @param data
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * getOrderBy
     *
     * @return
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * setOrderBy
     *
     * @param orderBy
     * @author yakirChen ~^o^~ <a href="http://pamirs.top">yakirchen.com</a>
     */
    public void setOrderBy(String orderBy) {
        if (orderBy != null && !orderBy.isEmpty()) {
            this.orderBy = orderBy;
        } else {
            this.orderBy = "id";
        }
    }
}
