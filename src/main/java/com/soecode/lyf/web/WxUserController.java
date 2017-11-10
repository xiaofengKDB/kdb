package com.soecode.lyf.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.lyf.common.Constant;
import com.soecode.lyf.common.CookieUtil;
import com.soecode.lyf.entity.WxUser;
import com.soecode.lyf.service.WeCatchCoreService;

@Controller
@RequestMapping("/wxUser")
public class WxUserController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxUserController.class);
	
	@Resource
	private WeCatchCoreService weCatchCoreService;
	
	/**
	 * 用户授权后重定向的回调链接地址
     *  具体而言，网页授权流程分为四步：
		1、引导用户进入授权页面同意授权，获取code
    	2、通过code换取网页授权access_token（与基础支持中的access_token不同）
    	3、如果需要，开发者可以刷新网页授权access_token，避免过期
    	4、通过网页授权access_token和openid获取用户基本信息（支持UnionID机制）
	 * @author 406937 刘凡
	 * @createTime 2017年11月1日 下午2:40:16
	 * @param
	 */
	@RequestMapping(value="/getUserInfo", method=RequestMethod.GET)
	public void getUserInfo(HttpServletRequest rq,HttpServletResponse rp){
		String code=null;
		long start = System.currentTimeMillis();
		HashMap<String, Object> data = new HashMap<String,Object>();
		
		try {
			String rqUrl = rq.getParameter("url");
			String url = rq.getRequestURL().toString();
			//从url中获取 所有参数 参数封装为map  从map中获取key为code的值得到code 
			//若无code 则需要授权拿到code
			System.out.println("回调URL："+rqUrl);
			//对url进行URLEncode转换
			String strUrl = URLEncoder.encode(url, "utf-8");
			//得到请求的参数Map，注意map的value是String数组类型  
			Map map = rq.getParameterMap();
			code = maptoCode(map, code);
			if(null == code || code.equals("")){
				//获取code
				//引导用户进入授权页面同意授权，获得code
				String jumpPath = weCatchCoreService.getCode(strUrl+"?url="+rqUrl);
				rp.sendRedirect(jumpPath);
				return;
			}else{
				//拿到code后 通过code获取网页授权access_token（与基础支持中的access_token不同）
				Map<String, String> userMap = weCatchCoreService.getAccessToken(code);
				String openId = userMap.get("openid");
				//判断用户是否存在
				WxUser userInfo;
				//如果数据库里有用户信息的话，表示用户已经注册过了
				userInfo = weCatchCoreService.getWxUser(openId);
				if(userInfo == null){
					//通过微信接口获取用户信息，可能获取用户信息失败
					userInfo = weCatchCoreService.getWechatUserFromUnionID(userMap);
					//如果接口异常，没获取到用户信息
					if (userInfo == null) {
						//把用户跳转到错误页面
						rp.sendRedirect("./wxUser/error");
						return;
					}
				}
				if(userInfo != null){
					//插入缓存
					//获取cookie里的token值
					Cookie cookie = CookieUtil.getCookieByName(rq, Constant.KEY_OPEN_ID);
					String cookieValue = null;
					if (cookie != null) {
						cookieValue = cookie.getValue();
						if(!cookieValue.equals(userInfo.getOpenid())){
							CookieUtil.editCookie(rp, rq, Constant.KEY_OPEN_ID, openId, "/", 60*60*24);
						}
					}else {
						CookieUtil.addCookie(rp, Constant.KEY_OPEN_ID, openId, "/", 60*60*24);
					}
					long end = System.currentTimeMillis();
					System.out.println("获取微信用户时间："+(end-start));
					logger.info("获取微信用户时间："+(end-start));
				}
				if(StringUtils.isNotBlank(rqUrl) && rqUrl.contains("prodetail")){
					rp.sendRedirect(rqUrl);
				}else{
					rp.sendRedirect("./addExpressOrder");
				}
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String maptoCode(Map map,String code){
    	Set<String> keySet = map.keySet();  
        for (String key : keySet) {  
 	        String[] values = (String[]) map.get(key);  
 	        for (String value : values) {  
 	            System.out.println(key+"="+value);  
 	        	if(key.equals("code")){
 	        		code= value;
 	        	}
 	        }
         }
         return code;
    }

	@RequestMapping("/error")
	public ModelAndView toError(){
		ModelAndView modelAndView = new ModelAndView("/error.html");
		return modelAndView;
	}
	
	@RequestMapping("/addExpressOrder")
	public ModelAndView toAddExpressOrder(){
		ModelAndView modelAndView = new ModelAndView("/addExpressOrder.html");
		return modelAndView;
	}
}
