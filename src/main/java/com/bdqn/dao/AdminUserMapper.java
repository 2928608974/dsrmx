package com.bdqn.dao;

import com.bdqn.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {

    List<AdminUser> findAdminUserAll();

    AdminUser selAdminUserByName(@Param("loginName") String loginName);

}
