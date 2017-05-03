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
public enum UserTypeEnum implements IEnum<Integer> {
    FREE_VERSION(0,"免费版"),
    CHARGE_VERSION(1,"收费版");

    private int value;
    private String desc;

    UserTypeEnum(int value, String desc) {
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

    private static Map<Integer, UserTypeEnum> mappings = new HashMap<>();
    static {
        for (UserTypeEnum workState : UserTypeEnum.values()) {
            mappings.put(workState.getValue(), workState);
        }
    }

    public static UserTypeEnum resolve(int workState) {
        return mappings.get(workState);
    }
}
