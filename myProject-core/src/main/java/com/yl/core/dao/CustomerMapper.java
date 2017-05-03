/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.dao;

import com.yl.core.bean.CustomerBO;
import com.yl.core.bean.CustomerParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/24
 * @description
 */
@Repository
public interface CustomerMapper {

    List<CustomerBO> selectByPage(CustomerParam param);
}
