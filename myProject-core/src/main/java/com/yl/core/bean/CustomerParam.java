/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.bean;

import com.zhixindu.commons.page.PageParam;

import java.io.Serializable;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
public class CustomerParam implements Serializable,PageParam {

    private static final long serialVersionUID = -2399080127866298447L;
    private String name;
    private String mobile;
    /**页数**/
    private int page = 0;
    /**每页大小**/
    private int count = 10;

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

    @Override
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
