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
public enum VerifiedStatusEnum implements IEnum<Integer>{

    NO_VERIFIED(0,"未认证"),
    VERIFIED_SUCCESS(1,"认证成功"),
    VERIFIED_FAIL(2,"认证失败");

    private int value;
    private String desc;

    VerifiedStatusEnum(int value, String desc) {
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

    private static Map<Integer, VerifiedStatusEnum> mappings = new HashMap<>();
    static {
        for (VerifiedStatusEnum workState : VerifiedStatusEnum.values()) {
            mappings.put(workState.getValue(), workState);
        }
    }

    public static VerifiedStatusEnum resolve(int workState) {
        return mappings.get(workState);
    }
}
