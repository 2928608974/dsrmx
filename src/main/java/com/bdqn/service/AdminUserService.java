package com.bdqn.service;

import com.bdqn.pojo.AdminUser;
import com.bdqn.pojo.FrontUser;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface AdminUserService {

    List<AdminUser> findAdminUserAll();

    AdminUser selAdminUserByName(@Param("name") String name);

    /**
     * 文件上传
     * @param is
     */
    void doImport(InputStream is);

    /**
     * 文件导出
     * @param os 输出流
     * @param
     */
    void userExport(OutputStream os, FrontUser front);



}
