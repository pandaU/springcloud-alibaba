package com.pandau.cloud.mapper;

import com.pandau.cloud.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT id,name,age FROM user")
    List<UserInfo> getUserInfos();

    @Insert("INSERT INTO user (name,age) VALUES(#{name},#{age})")
    int addUserInfo(UserInfo info);
}
