package com.lm.api.model;


import java.io.Serializable;

/**
 * 用户表
 */
public class User implements Serializable {

    private Integer id;
    /**
     * 微信open id
     */
    private String openid;
    /**
     * 名字
     */
    private String name;
    /**
     * 密码
     */
    private transient String pwd;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 手机
     */
    private String phone;

    private String sign;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 城市
     */
    private String city;
    /**
     * 新增日期
     */
    private java.util.Date createTime;
    /**
     * 用户账号
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}
