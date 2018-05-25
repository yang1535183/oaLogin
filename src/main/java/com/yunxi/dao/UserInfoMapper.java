package com.yunxi.dao;

import org.springframework.stereotype.Repository;

import com.yunxi.model.UserInfo;

@Repository("UserDao")
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo getUserByUserName(String userName);
}