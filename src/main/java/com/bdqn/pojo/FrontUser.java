/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.8
 */

package com.bdqn.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontUser implements Serializable {

    /** 用户编号 */
    private Integer userId;

    /** 用户姓名 */
    private String userName;

    /** 身份证号码 */
    private String idCard;

    /** 联系电话 */
    private String phone;

    /** 身份编号ID */
    private String provinceData;

    /** 省份名称 */
    private String province;

    /** 市区编号ID */
    private String cityData;

    /** 市区名称 */
    private String city;

    /** 县编号ID */
    private String areaData;

    /** 县名称 */
    private String area;

    /** 邮寄地址 */
    private String address;

    /** 金额，精确到小数点后2位 */
    private Double money;

    @Override
    public String toString() {
        return "FrontUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceData='" + provinceData + '\'' +
                ", province='" + province + '\'' +
                ", cityData='" + cityData + '\'' +
                ", city='" + city + '\'' +
                ", areaData='" + areaData + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", money=" + money +
                ", idJust='" + idJust + '\'' +
                ", idAgainst='" + idAgainst + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceData() {
        return provinceData;
    }

    public void setProvinceData(String provinceData) {
        this.provinceData = provinceData;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityData() {
        return cityData;
    }

    public void setCityData(String cityData) {
        this.cityData = cityData;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaData() {
        return areaData;
    }

    public void setAreaData(String areaData) {
        this.areaData = areaData;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getIdJust() {
        return idJust;
    }

    public void setIdJust(String idJust) {
        this.idJust = idJust;
    }

    public String getIdAgainst() {
        return idAgainst;
    }

    public void setIdAgainst(String idAgainst) {
        this.idAgainst = idAgainst;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 身份证正面 */
    private String idJust;

    /** 身份证反面 */
    private String idAgainst;

    /** 是否确认状态  0确认   1未确认 */
    private Integer state;

    /** 备注 */
    private String remark;

}