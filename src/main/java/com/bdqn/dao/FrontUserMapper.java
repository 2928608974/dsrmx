package com.bdqn.dao;

import com.bdqn.pojo.FrontUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FrontUserMapper {
    /**
     * 查询用户信息
     *
     * @return 返回查询到的集合
     */
    List<FrontUser> selUser(FrontUser font);

    /**
     * 新增用户
     * @param front
     * @return
     */
    int addUser(FrontUser front);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    int delBatch(@Param("ids") String[] ids);

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
    FrontUser findById(@Param("uid") Integer uid);

    /**
     * 前台登录
     */
    FrontUser loginUser(@Param("userName")String userName,@Param("idCard")String idCard,@Param("phone")String phone);

}
