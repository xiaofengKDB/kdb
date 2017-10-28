package com.soecode.lyf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soecode.lyf.common.Result;
import com.soecode.lyf.dao.UserMapper;
import com.soecode.lyf.dto.MD5Util;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userDao;

	@Override
	public Result<User> checkLogin(String userName, String passWord) {
		Result<User> result = new Result<User>();
		User user = userDao.selectByPrimaryKey(userName);
		String md5 = MD5Util.getMD5(passWord);
		if(null != user && user.getPassword().equals(md5)){
			result.setSuccess(true);
			result.setData(user);
		}else{
			result.setSuccess(false);
			result.setError(0,"userError","用户名或者密码错误");
		}
		return result;
	}

}
