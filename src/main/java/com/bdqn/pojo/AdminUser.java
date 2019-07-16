

package com.bdqn.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUser implements Serializable {

    /** 用户编号 */
    private Integer userId;

    /** 登录名 */
    private String loginName;

    /** 用户名称 */
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    /** 用户密码 */
    private String userPassword;

    /** 备注 */
    private String remark;
}