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
 * @date 2017/4/26
 * @description
 */
public class CreditConfigParam implements PageParam,Serializable {

    private static final long serialVersionUID = -6487684445530368286L;
    private int page;
    private int count;

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
