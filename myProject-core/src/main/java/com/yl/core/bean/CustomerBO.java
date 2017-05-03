/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.bean;

import com.yl.core.bean.enums.UserRoleEnum;
import com.yl.core.bean.enums.UserTypeEnum;
import com.yl.core.bean.enums.VerifiedStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/24
 * @description
 */
public class CustomerBO implements Serializable {
    private static final long serialVersionUID = 5439575695909873911L;

    private String id;
    private String name;
    private String mobile;
    private String id_no;
    private Date register_time;
    private Date update_time;
    private String open_id;
    private UserTypeEnum user_type;//用户类型(0:免费版  1:收费版)
    private String pay_password;
    private Date create_time;
    private UserRoleEnum user_role;//0:注册用户 1：实名用户 2：客户经理 3：管理员管理员
    private Date upgrade_time;
    private VerifiedStatusEnum verified_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public UserTypeEnum getUser_type() {
        return user_type;
    }

    public void setUser_type(UserTypeEnum user_type) {
        this.user_type = user_type;
    }

    public String getPay_password() {
        return pay_password;
    }

    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public UserRoleEnum getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRoleEnum user_role) {
        this.user_role = user_role;
    }

    public Date getUpgrade_time() {
        return upgrade_time;
    }

    public void setUpgrade_time(Date upgrade_time) {
        this.upgrade_time = upgrade_time;
    }

    public VerifiedStatusEnum getVerified_status() {
        return verified_status;
    }

    public void setVerified_status(VerifiedStatusEnum verified_status) {
        this.verified_status = verified_status;
    }
}
