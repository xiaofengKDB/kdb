package com.soecode.lyf.service;

import com.soecode.lyf.common.Result;
import com.soecode.lyf.entity.User;

public interface UserService {

	Result<User> checkLogin(String userName, String passWord);

}
