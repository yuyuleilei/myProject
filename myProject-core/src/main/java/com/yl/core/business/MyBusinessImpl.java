/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.business;

import com.zhixindu.commons.annotation.Business;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/24
 * @description
 */
@Business("myBusiness")
public class MyBusinessImpl implements MyBusiness {

    @Override
    public String getMyName() {
        return "测试啊啊，你妹的，打死蜜蜜的";
    }

}
