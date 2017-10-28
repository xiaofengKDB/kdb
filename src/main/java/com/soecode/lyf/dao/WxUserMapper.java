package com.soecode.lyf.dao;

import com.soecode.lyf.entity.WxUser;

public interface WxUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);
}