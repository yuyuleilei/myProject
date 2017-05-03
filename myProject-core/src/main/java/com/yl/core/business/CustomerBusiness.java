/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.business;

import com.yl.core.bean.CustomerBO;
import com.yl.core.bean.CustomerParam;
import com.zhixindu.commons.page.PageResult;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
public interface CustomerBusiness {
    PageResult<CustomerBO> selectByPage(CustomerParam param);
}
