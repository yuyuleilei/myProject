/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.bean.enums;

import com.zhixindu.commons.api.IEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/24
 * @description
 */
public enum UserRoleEnum implements IEnum<Integer> {

    REGISTER_USER(0,"注册用户"),
    REALNAME_USER(1,"实名用户"),
    CUSTOMER_MANAGE_USER(2,"客户经理"),
    ADMINISTRATOR_USER(3,"管理员");

    private int value;
    private String desc;

    UserRoleEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public boolean matches(Integer value) {
        if(null == value) {
            return false;
        }
        return getValue().intValue() == value.intValue();
    }

    @Override
    public boolean matches(IEnum<Integer> valueBean) {
        if(null == valueBean) {
            return false;
        }
        return matches(valueBean.getValue());
    }

    private static Map<Integer, UserRoleEnum> mappings = new HashMap<>();
    static {
        for (UserRoleEnum workState : UserRoleEnum.values()) {
            mappings.put(workState.getValue(), workState);
        }
    }

    public static UserRoleEnum resolve(int workState) {
        return mappings.get(workState);
    }
}
