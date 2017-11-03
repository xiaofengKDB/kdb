package com.soecode.lyf.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {

//    public static final int MODE_NORMAL = 0;
//    public static final int MODE_DEBUG = 1;
//    public static final String PREFIX = "/";
//    public static final int NO_VALUE_ALTERNATIVE = -(1<<24)-1;
//    public static final int SH_STOCK = 0;
//    public static final int SZ_STOCK = 1;
//    public static final int SORT_TYPE_CHANGRATE_UP = 1;
//    public static final int SORT_TYPE_CHANGRATE_DOWN = 2;
//    public static final int SORT_TYPE_AMOUNT_UP = 3;
//    public static final int SORT_TYPE_AMOUNT_DOWN = 4;
//    public static final int ALL_STOCK = 2;
//    public static final String SZ_STOCK_INDEX = "399001";
//    public static final String SH_STOCK_INDEX = "000001";
//    public static final Map<String,String> PLATE_MAP = new HashMap<String,String>();
//    public static final Map<Integer,String> USER_LEVER = new HashMap<Integer,String>();
    
    /**
     * 商品url前缀
     * 发布生产时：此地址需要修改
     */
//  	public static final String HTTP = "http://localhost:8080/goods/mobile/content/";
  	/**
  	 * 商品url后缀
  	 */
//  	public static final String HTML = ".html";
  	/**
  	 * 分类list跳转 url 前缀
  	 */
//  	public static final String PATH_PREFIX = "/goods/moblie/list/";
  	/**
  	 * 分类list跳转 url 后缀
  	 */
//  	public static final String PATH_SUFFIX = ".jhtml";
  	/**
  	 * 微信生成静态页面的模版路径
  	 */
//  	public static final String TEMPLATEPATH = "/shop/mobile/goods/content.ftl";
  	/**
  	 * 微信模版生成html的html路径
  	 */
//  	public static final String STATIC_PATH = "/goods/mobile/content/";
  	/**
  	 * 商品的最大数量
  	 */
//  	public static final Integer MAX_QUANTITY = 10000;
  	/**
  	 * cookie 中存入的购物车key值
  	 */
//  	public static final String KEY_COOKIE_NAME = "cartKey";
  	/**
  	 * 购物车超时时间
  	 */
//  	public static final int TIMEOUT = 604800;
//  	
//  	public static final String SUCCESS = "success";
//
//  	public static final String WARN = "warn";

  	/**
  	 * 最大收货地址数量
  	 */
//  	public static final int MAX_FAVORITE_COUNT=10;
  	
  	/**
  	 * 浏览历史最大展示条数
  	 */
//  	public static final int MAX_LOOK_COUNT = 20;
  	/**
  	 * openId 的key
  	 */
  	public static final String KEY_OPEN_ID = "openId";
  	/**
  	 * ACCESS_TOKEN 的key
  	 */
  	public static final String KEY_TOKEN = "accessToken";
  	/**
  	 * ACCESS_JSAPITICKET 的key
  	 */
  	public static final String KEY_JSAPITICKET = "jsapiTicket";
  	/**
  	 * 插入数据时版本
  	 */
//  	public static final Long VERSION = 0L;
  	/**
  	 *保存cart 的key 
  	 */
//	public static final String CART = "cart";
	/**
	 * 最近的服务点距离
	 */
//	public static final double DISTANCE = 50;
	
	/**
	 * 定位和绑定服务点距离
	 */
//	public static final double DW_DISTANCE = 50;
	/**
	 * 微信请求参数
	 */
	//微信配置
	//微信公众号的app_id，唯一
	public static final String APP_ID="wx66c06ce0f439d91b";
	//微信公众号的secret，唯一
	public static final String SECRET="0f1dd513bfe064d2c52b81304b08ea4b";
	//微信公众号获取code的返回类型，固定
	public static final String RESPONSE_TYPE="code";
	//应用授权作用域,静默授权方式
	public static final String SCOPE_TYPE_BASE="snsapi_base";
	//应用授权作用域，手动确认方式
	public static final String SCOPE_TYPE_USERINFO="snsapi_userinfo";
	//可不写，重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值
	public static final String STATE="STATE";
	//必填，固定值
	public static final String GRANT_TYPE="authorization_code";
	//
	public static final String ACCEPT="text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	//
	public static final String USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0";
	//
	public static final String CONNECTION="keep-alive";
	//通过code获取access_token和openid的微信请求前缀
	public static final String GET_BY_CODE="https://api.weixin.qq.com/sns/oauth2/access_token?";
	//获取code的微信请求前缀
	public static final String GET_CODE="https://open.weixin.qq.com/connect/oauth2/authorize?";
	//从微信公众平台接口获取用户基本信息（网页授权）前缀
	public static final String GET_USERINFO_WEB="https://api.weixin.qq.com/sns/userinfo?";
	//获取微信用户信息UnionID机制前缀
	public static final String GET_USERINFO_UNIONID="https://api.weixin.qq.com/cgi-bin/user/info?";
	//返回国家地区语言版本
	public static final String WEIXIN_LANG="zh_CN";
	
	//定时任务间隔时间 （小时）
//	public static final int TIMED_TASK_SPACE=1;
	
	//获取全局access_token的微信请求前缀
	public static final String GET_GLOBAL_ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?";
		
	//全局
	public static final String GLOBAL_GRANT_TYPE="client_credential";
		
    
    
//    static {
//    	PLATE_MAP.put("0", "getRiseRange");
//    	PLATE_MAP.put("1", "getPrice");
//    	PLATE_MAP.put("2", "getAvgPrice");
//    	PLATE_MAP.put("3", "getRise");
//    	PLATE_MAP.put("4", "getAmount");
//    	PLATE_MAP.put("5", "getPower");
//    	PLATE_MAP.put("6", "getTop1");
//    	
//    	USER_LEVER.put(1, "当前客户端版本为免费版，请前往商城升级客户端版本");
//    	USER_LEVER.put(2, "当前客户端版本为诊股版，请前往商城升级客户端版本");
//    	USER_LEVER.put(3, "当前客户端版本为诊股版（三年以内），请前往商城升级客户端版本");
//    	USER_LEVER.put(5, "当前客户端版本为诊股18版，请前往商城升级客户端版本");
//    	USER_LEVER.put(4, "当前客户端版本为诊股版（三年以内），请前往商城升级客户端版本");
//    }
    
//    public static int getModeDebug() {
//        return MODE_DEBUG;
//    }
//    public static int getModeNormal() {
//        return MODE_NORMAL;
//    }
    
//    public static final int UNALARMED = 0;
//    public static final int ALARMED = 1;
//    
//    public static final int STOCK_CHUQUAN = 0;
//    public static final int STOCK_BEFORE_FUQUAN = 1;
//    public static final int STOCK_AFTER_FUQUAN = 2;
//	public static final String SMS_CODE = "1";
	
	
    
//	public interface PlatForm {
//		public static final String ALL = "all";
//		public static final String PC = "pc";
//		public static final String WEIXIN = "wx";
//	}
	/**
	 * 首页商品类型
	 * @author zhangchao
	 *
	 */
//	public interface IndexType{
//		public static final String PRO = "pro";
//		public static final String HOT = "hot";
//		public static final String NEW = "new";
//	}
	/**
	 * 添加购物车状态
	 * @author Administrator
	 *
	 */
//	public interface CartStatus {
//		public static final String NOSTOCK = "该商品库存不足！";
//	}

	/**
	 * 微信支付插件
	 * @author Administrator
	 *
	 */
	public interface WENCHAT_PAYMENT {
		public static final String PLUGIN_ID = "wenchatPaymentPlugin";
		public static final String PLUGIN_NAME = "微信支付";
	}
	
	/**
	 * 订单日志内容信息
	 * @author Administrator
	 *
	 */
//	public interface ORDER_LOG_CONTENT {
//		public static final String CREATE = "订单提交成功";
//		public static final String PAYMENT = "订单已支付，等待商家发货";
//		public static final String SHIPPING_COMMON = "商家已发货，等待顾客接收";
//		public static final String SHIPPING_SERV = "商家已发货，等待服务点接收";
//		public static final String RECEIVE_SERV = "服务点已收货，等待配送/自提";
//		public static final String COMPLETE = "顾客已收货，订单已完成";
//		public static final String CANCEL = "订单已取消";
//	}
	
	/**
	 * 会员表_用户类型
	 * @author Administrator
	 *
	 */
//	public interface MEMBER_TYPE{
//		public static final Integer COMMON = 0;//普通用户
//		public static final Integer SERVER = 1;//服务点用户		
//	}
	
	/**
	 * 会员表_营销模式
	 * @author Administrator
	 *
	 */
//	public interface SALE_MODEL{
//		public static final Integer DIRECT = 0;//直销模式
//		public static final Integer AGENT = 1;//代购模式		
//
//	}
	
	/**
	 * 促销表_促销类型
	 * @author Administrator
	 *
	 */
//	public interface PROMOTION_TYPE{
//		public static final Integer FULL_MINUS_MONEY = 0;	//满就减金额
//		public static final Integer FULL_GIVE_GIFTS = 1;	//满就送礼品	
//	}
//	
	/**
	 * TAG_标签名称
	 * @author Administrator
	 *
	 */
//	public interface TAG_NAME{
//		public static final String HOT = "热卖";
//		public static final String NEW = "最新";
//		public static final String PRO = "促销";
//	}
//	
	
//	public interface PaymentMethodId{
//		public static final Integer WENCHAT_PAYMENT = 4;			
//	}
//	
//	public interface ShippingMethodId{
//		public static final Integer SEND_TO_FAMILY = 3;//送货上门			
//		public static final Integer GET_FROM_SERVER = 4;//服务点自提		
//	}
	
	/**
	 * 定位类型
	 * @author Administrator
	 *
	 */
//	public interface DW_TYPE{
//		public static final Integer DW_DIRECT = 0;	//直销模式
//		public static final Integer DW_BIND = 1;	//绑定代购点
//		public static final Integer DW_MORE = 2;	//多个绑定代购点
//	}
	
	/**
	 * 提示类型
	 * @author Administrator
	 *
	 */
//	public interface POINT_TYPE{
//		public static final Integer POINT_FIRST = 0;	//第一次绑定
//		public static final Integer POINT_CHG = 1;	//切换服务点
//		public static final Integer POINT_DIR = 2;	//多个绑定代购点
//	}
	
	/**
	 *  体积单位
	 * @author Administrator
	 *
	 */
//	public interface VOLUME_UNIT{
//		public static final String SHENG = "升";//升			
//		public static final String LFM = "立方米";//立方米					
//	}
	
//	public interface QueryGoodsType{
//		public static final String CUSTOMER_GOODS = "costomerGoods";//普通商品			
//		public static final String OP_GOODS = "opGoods";//运营中心商品
//		public static final String SUGGEST_GOODS = "suggest_opGoods";//智能提示商品
//		public static final String CUSTOMER_SUGGEST_GOODS = "customer_suggest_opGoods";//普通用户根据Id查	
//		public static final String OP_SUGGEST_GOODS = "op_suggest_opGoods";//运营中心用户根据Id查		
//	}
	/**
	 * 购物车选中状态
	 * @author Administrator
	 *
	 */
//	public interface CHECKED_TYPE{
//		public static final String ALL = "all";
//		public static final String SINGLE = "single";
//	}
	
	/**
	 * 购物车选中状态
	 * @author Administrator
	 *
	 */
//	public interface EDIT_TYPE{
//		public static final String ADD = "add";
//		public static final String SUB = "sub";
//	}
	
}
