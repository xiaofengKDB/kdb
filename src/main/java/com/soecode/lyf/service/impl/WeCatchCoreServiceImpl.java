package com.soecode.lyf.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soecode.lyf.common.Constant;
import com.soecode.lyf.dao.WxUserMapper;
import com.soecode.lyf.entity.WxUser;
import com.soecode.lyf.service.WeCatchCoreService;

@Service
public class WeCatchCoreServiceImpl implements WeCatchCoreService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private WxUserMapper wxUserDao;
	
	/**
	 * 对传入的URL进行维修请求地址拼接
	 */
	@Override
	public String getCode(String url) {
		logger.info("对传入的url进行微信请求地址拼接");
		try {
			System.out.println(url);
			//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60
			//&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code
			//		&scope=snsapi_userinfo&state=STATE#wechat_redirects
			String strUrl=Constant.GET_CODE+"appid="+Constant.APP_ID+"&redirect_uri="+url+
					"&response_type="+Constant.RESPONSE_TYPE+"&scope="+Constant.SCOPE_TYPE_BASE+
					"&state="+Constant.STATE+"#wechat_redirect";//http://wx.qq.com
			return strUrl;
		} catch (Exception e) {
			logger.error("getCode error!", e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过code获取access_token和openid
	 * 然后获取用户信息并保存
	 */
	@Override
	public Map<String, String> getAccessToken(String code) throws Exception {
		logger.info("通过code获取access_token和openid");
		// 通过code换取网页授权access_token
		StringBuffer buffer = new StringBuffer();// 用来拼接参数
		StringBuffer result = new StringBuffer();// 用来接受返回值
		URL httpUrl = null;// HTTP URL类 用这个类来创建连接
		URLConnection connection = null;// 创建的http连接
		BufferedReader bufferedReader = null;// 接受连接受的参数
		buffer.append(Constant.GET_BY_CODE+"appid="+Constant.APP_ID+"&secret="+Constant.SECRET+
				"&grant_type="+Constant.GRANT_TYPE);
		if(null != code && !code.equals("")){
			buffer.append("&code=").append(code);
		}
		logger.info("-----获取token连接"+buffer);
		//获取用户信息
		//创建URL
		httpUrl = new URL(buffer.toString());
		connection = httpUrl.openConnection();
		connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.connect();
		//接收连接返回参数
		bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		while((line = bufferedReader.readLine()) != null){
			result.append(line);
		}
		bufferedReader.close();
		JSONObject obj = JSONObject.parseObject(result.toString());
		String access_token = obj.get("access_token")+"";
		String openId = obj.get("openid") + "";
		System.out.println("access_token:"+access_token+"----openid:"+openId);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("access_token", access_token);
		map.put("openid", openId);
		
		return map;
	}

	@Override
	public WxUser requestUserInfoFromWeChat(Map<String, String> userMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxUser insertUser(WxUser wechatUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxUser getWxUser(String openid) {
		WxUser wxUser = null;
		try {
			wxUser = wxUserDao.selectByPrimaryKey(openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("从数据库获取微信用户信息异常"+e.getMessage());
			return null;
		}
		logger.info("从数据库获取用户信息"+JSON.toJSONString(wxUser));
		return wxUser;
	}

	@Override
	public void insertRedisClient(String key, WxUser wechatUser, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String processRequestXml(String xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendMessageToUser(String openid, String messageType, String order_number, String order_time,
			String pickup_code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAccessToken1() {
		String access_token = "";
		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer result = new StringBuffer();
			URL httpUrl = null;
			URLConnection connection = null;
			BufferedReader bufferedReader = null;
			buffer.append(Constant.GET_GLOBAL_ACCESS_TOKEN+"grant_type="+Constant.GLOBAL_GRANT_TYPE+
					"&appid="+Constant.APP_ID+"&secret="+Constant.SECRET);
			httpUrl = new URL(buffer.toString());
			logger.info("获取全局access_token请求地址："+httpUrl);
			connection = httpUrl.openConnection();
			connection.setRequestProperty("accept", Constant.ACCEPT);
			connection.setRequestProperty("connection", Constant.CONNECTION);
			connection.setRequestProperty("user-agent", Constant.USER_AGENT);
			connection.connect();
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				result.append(line);
			}
			bufferedReader.close();
			JSONObject obj = JSONObject.parseObject(result.toString());
			access_token = obj.get("access_token")+"";
			logger.info("获取到的access_token值：{}"+access_token);
			return access_token;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取全局access_token失败");
			return null;
		}
	}

	@Override
	public WxUser getWechatUserFromUnionID(Map<String, String> map) {
		logger.info("获取微信用户信息UnionId机制"+map);	
		try {
			String openid=map.get("openid");
			/*WechatUser wxuser=getWechatUser(openid);
			if(null!=wxuser){
				return wxuser;
			}*/
			String access_token=getAccessToken1();
			if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(access_token)){
				return null;
			}
			// 拉取用户信息
			StringBuffer result = new StringBuffer();
			StringBuffer buffer = new StringBuffer();
			URL httpUrl = null; // HTTP URL类 用这个类来创建连接
			URLConnection connection = null; // 创建的http连接
			BufferedReader bufferedReader = null; // 接受连接受的参数
			//拼接微信URL连接地址
			buffer.append(Constant.GET_USERINFO_UNIONID+"access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
			httpUrl = new URL(buffer.toString());
			logger.info("拉取微信用户信息请求地址："+httpUrl);
			// 建立连接
			connection = httpUrl.openConnection();
			connection.setRequestProperty("accept",Constant.ACCEPT);
			connection.setRequestProperty("connection", Constant.CONNECTION);
			connection.setRequestProperty("user-agent",Constant.USER_AGENT);
			connection.connect();
			// 接收连接返回参数
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			bufferedReader.close();
			JSONObject obj = JSONObject.parseObject(result.toString());
			logger.info("获取微信用户信息的json字符串为："+obj.toString());
			System.out.println(obj.toString());
			if (obj.containsKey("errcode")) {
				return null;
			}
			String openids = obj.get("openid")+"";
			String nickname = obj.get("nickname")+"";
			String sex = obj.get("sex")+"";
			String province = obj.get("province")+"";
			String city = obj.get("city")+"";
			String country = obj.get("country")+"";
			String headimgurl = obj.get("headimgurl")+"";
			WxUser wxUser = new WxUser();
			wxUser.setOpenid(openids);
			wxUser.setNickname(nickname);
			if(null != sex && !sex.equals("null") && !sex.equals("")){
				wxUser.setSex(Integer.valueOf(sex));
			}
			wxUser.setProvince(province);
			wxUser.setCity(city);
			wxUser.setCountry(country);
			wxUser.setHeadimgurl(headimgurl);
			logger.info("获取微信用户信息："+JSON.toJSONString(wxUser));
			wxUserDao.insertSelective(wxUser);
			return wxUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WxUser getWechatUser(String opendId) {
		// TODO Auto-generated method stub
		return null;
	}

}
