package com.soecode.lyf.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.soecode.lyf.dto.RequestParameter;
import com.soecode.lyf.dto.ResponseMsg;
import com.soecode.lyf.dto.Result;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.UserService;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg login(@RequestBody RequestParameter parameter, RedirectAttributes redirect,
			HttpSession session){
		//提示信息
		ResponseMsg msg = new ResponseMsg();
		//获取请求参数
		String conditonStr = parameter.getSearchCondition();
		HashMap condition = JSON.parseObject(conditonStr,
				HashMap.class);
		String userName = (String) condition.get("username");//登录用户名
		String passWord = (String) condition.get("passWord");//登录密码
		//判断用户名和密码不能为空
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
			return errorMsg(msg,"用户名或密码未输入");
		}
		//调用中台服务返回结果对象
		Result<User> result = null;
		try{
			result = userService.checkLogin(userName,passWord);
		}catch(Exception e){
			errorMsg(msg, "系统异常");
		}
		return loginInfo(session, msg, passWord, result);
	}
	
	/**
	 * login
	 *
	 * @Title: login
	 * @Description: 进入到登陆页面
	 * @author yangdechao
	 * @param userName
	 * @return 设定文件
	 * @throws
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("/login.html");
		//先清理当前session
		HttpSession session = request.getSession();
		if(session != null){
			if (request.getSession().getAttribute("userName") != null) {
				//如果登陆过了直接跳转首页
				mv = new ModelAndView("redirect:/index");
			}else{
				session.invalidate();
			}
		}
		return mv;
	}
	
	private ResponseMsg errorMsg(ResponseMsg msg,String errormsg) {
		msg.errorCode = -2;
		msg.msg =errormsg;
		return msg;
	}
	@SuppressWarnings("unused")
	private ResponseMsg loginInfo(HttpSession session, ResponseMsg msg, String userName, Result<User> result) {
		if(result==null){
			logger.error("系统异常:"+userName);
			return errorMsg(msg,"系统异常");
		}
		if(!result.isSuccess()){//根据用户名和密码不成功 则判断用户是否存在
			return errorMsg(msg,result.getError());
		}
		User user = result.getData();//获取存放的用户信息
		if (user == null) {//如果没有用户直接返回错误
			return errorMsg(msg,"该用户不存在");
		}
		if(user.getIsactivate() != 1){
			return errorMsg(msg, "用户未激活，请联系管理员激活");
		}
//		try{
//					//根据用户工号获取用户的权限集合
//			ArrayList<String> functionCodes = (ArrayList<String>)functionService.getFunctionCodeList(user.getEmpCode());
//			session.setAttribute("authFunctionCodes", functionCodes); //存放至权限编码集合中
//			
//			//根据工号获取用户授权的uri集合
//			HashMap<String, String> functionUriMap = (HashMap<String, String>)functionService.getUserFunctionUri(user.getEmpCode());
//			session.setAttribute("authUriMap", functionUriMap); 
//		
//			//获取所有功能权限的uri集合
//			HashMap<String, String> allFunctionUriMap = (HashMap<String, String>)functionService.getAllFunctionUri();
//			session.setAttribute("allUriMap", allFunctionUriMap); 
//			
//			//获取用户所有的角色集合并存储session中
//			Result<List<RoleInfoDO>> queryRoleInfoByUserCode = 
//					roleInfoService.queryRoleInfoByUserCode(user.getEmpCode());
//			if(queryRoleInfoByUserCode==null){
//				logger.error("角色信息基础服务不在线:"+userName);
//				return errorMsg(msg,"角色信息基础服务不在线:");
//			}
//			if(!queryRoleInfoByUserCode.isSuccess()){
//				return errorMsg(msg,queryRoleInfoByUserCode.getErrorMessage());
//			}
//			session.setAttribute("userRoles",(ArrayList<RoleInfoDO>) queryRoleInfoByUserCode.getData());//存放用户角色
		
//			} catch (Exception e) {//如果调用失败 直接返回错误
//				logger.error("{}", e);
//				System.out.println(e.getMessage());
//				session.setAttribute("authFunctionCodes", null); 
//				session.setAttribute("authUriMap", null); 
//				msg.data=e.getMessage();
//				if(e.getMessage().contains("pretrade")){
//					logger.error("角色基础服务不在线:"+userName);
//					return errorMsg(msg,"角色基础服务不在线");					
//				}
//				logger.error("功能基础服务不在线:"+userName);
//				return errorMsg(msg,"功能基础服务不在线");
//			}
//			try{
//	//			根据部门编码获取部门 
//				Query<NameCodeInfoDO> q=new Query<NameCodeInfoDO>();
//				NameCodeInfoDO data=new NameCodeInfoDO();
//				data.setCode(user.getDeptCode());
//				q.setData(data);
//				//调用服务获取指定编码的部门信息
//				Result<DeptInfoDO> queryDeptOne = deptInfoService.queryDeptOne(user.getDeptCode());
//				if(queryDeptOne==null){
//					logger.error("部门基础服务不在线:"+userName);
//					return errorMsg(msg,"部门基础服务不在线");
//				}
//				DeptInfoDO deptInfoDO = queryDeptOne.getData();
//				
//				//如果查询出来的部门信息不为空，设置session
//				session.setAttribute("deptUnCode", deptInfoDO.getUnifiedCode());//存放部门标杆编码					
//				session.setAttribute("deptDO", deptInfoDO);//存放部门实体对象	
//				session.setAttribute("userDeptDO", deptInfoDO);//存放用户原始部门
//			} catch (Exception e) {//如果调用失败,直接抛出异常
//				logger.error("{}", e);
//				msg.data=e.getMessage();
//				logger.error("加载部门信息错误:"+userName);
//				return errorMsg(msg,"加载部门信息错误");
//			}
			session.setAttribute("user", user);//存放用户实体
			session.setAttribute("userName", user.getUsername());//存放用户登录名
			session.setAttribute("name", user.getName());//存放用户名字
			//存放用户原始部门信息，切换部门时使用
			
			msg.errorCode = 0;
			msg.msg = "操作成功";
	//		更新用户登录时间  如果用户的创建时间为空 那么也更新用户的创建时间
			logger.info("登录用户名为:{}", userName);
			return msg;
	}
	/**
	 * home
	 * @Title:index
	 * @Description: 跳转到主页面
	 * @author yangdechao
	 * @return 设定文件
	 * @throws
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session)
	{
		ModelAndView mv = new ModelAndView("/index.html");
		return mv;
	}
	
	/**
	 * 退出登录跳转
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session)
	{
		session.removeAttribute("user");
		session.removeAttribute("userName");
		session.removeAttribute("name");
		session.invalidate();
		ModelAndView mv = new ModelAndView("/login.html");
		return mv;
	}
}
