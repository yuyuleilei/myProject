/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.service;

import com.yl.core.bean.CreditCode;
import com.yl.core.bean.CreditConfigBase;
import com.yl.core.bean.CreditConfigParam;
import com.zhixindu.commons.page.PageResult;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
public interface CreditService {

    CreditCode findByCode(String code);
    PageResult<CreditConfigBase> searchCreditConfigByPage(CreditConfigParam param);
}
