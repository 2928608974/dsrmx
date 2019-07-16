package com.bdqn.service;

import com.bdqn.pojo.FrontUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FrontUserService {
    /**
     * 查询用户信息
     * @return 返回查询到的集合
     */
   List<FrontUser> selUser(FrontUser font);
    /**
     * 增加用户
     * @param frontUser 增加的对象
     * @return  返回受影响的行数
     */
    int addUser(FrontUser frontUser);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int delBatch(String[] ids);



    /**
     * 更新
     * @param
     * @return
     */
    int updateFrontUser(FrontUser user);

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    FrontUser findById(Integer uid);

    /**
     * 前台登录
     */
    FrontUser loginUser(@Param("userName")String userName,@Param("idCard")String idCard,@Param("phone")String phone);
}
