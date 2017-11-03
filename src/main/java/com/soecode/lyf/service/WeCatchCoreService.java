package com.soecode.lyf.service;

import java.util.Map;

import com.soecode.lyf.entity.WxUser;

public interface WeCatchCoreService {

	/**
	 * 获取code
	 * @param url 
	 * @return
	 */
	public String getCode(String url);
	/**
	 * 获取access_token
	 * @param code
	 * @return
	 */
	public Map<String,String> getAccessToken(String code)throws Exception;

	/**
	 * 从微信公众平台接口获取用户基本信息（网页授权）
	 */
	public WxUser requestUserInfoFromWeChat(Map<String,String> userMap) throws Exception;
	
	/**
	 * 保存用户信息
	 * @param WxUser
	 *//*
	public void insert(WxUser wechatUser);*/
	/**
	 * 保存用户并返回id值
	 * @param WxUser
	 * @return
	 */
	public WxUser insertUser(WxUser wechatUser);
	/**
	 * 通过token获取用户信息
	 * @param token(cookie存入的key值)
	 * @return
	 */
	public WxUser getWxUser(String tokenValue);
	/**
	 * 将用户信息插入缓存
	 * @param key
	 */
	public void insertRedisClient(String key,WxUser wechatUser,String value);
	
	/**
	 * 处理微信发来的请求
	 * @param xml
	 * @return
	 */
	public String processRequestXml(String xml);
	
	/**
	 * 发送客服消息
	 * @param openid 微信用户openid
	 * @param messageType 消息类型：1、下单成功：order_success，2、店铺自提：shop_since，3、送货上门：home_delivery
	 * @param order_number 订单号码
	 * @param order_time 支付时间
	 * @param pickup_code 提货码
	 * @return
	 */
	public boolean sendMessageToUser(String openid,String messageType,String order_number,String order_time,String pickup_code);
	
	/**
	 * 获取全局access_token
	 */
	public String getAccessToken1();
	
	/**
	 * unionID 获取用户信息
	 */
	public WxUser getWechatUserFromUnionID(Map<String, String> map);
	/**
	 * 通过token获取用户信息
	 * @param token(cookie存入的key值)
	 * @return
	 */
	public WxUser getWechatUser(String opendId);
}
