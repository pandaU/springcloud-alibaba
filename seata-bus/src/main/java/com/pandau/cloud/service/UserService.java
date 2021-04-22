package com.pandau.cloud.service;

import com.pandau.cloud.config.dynamicDataSource.Db;
import com.pandau.cloud.mapper.UserMapper;
import com.pandau.cloud.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    @Transactional
    @Db("master")
    public void addUser(UserInfo info){
        userMapper.addUserInfo(info);
    }

    @Db("slave")
    public List<UserInfo> getUserInfos(){
        return userMapper.getUserInfos();
    }
}
