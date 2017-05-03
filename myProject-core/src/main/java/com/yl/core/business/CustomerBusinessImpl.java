/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.business;

import com.yl.core.app.WebAppConfig;
import com.yl.core.bean.CustomerBO;
import com.yl.core.bean.CustomerParam;
import com.yl.core.dao.CustomerMapper;
import com.zhixindu.commons.annotation.Business;
import com.zhixindu.commons.client.JedisClient;
import com.zhixindu.commons.page.PageResult;
import com.zhixindu.commons.repository.PageRepository;
import com.zhixindu.commons.utils.JsonUtil;
import com.zhixindu.commons.utils.Parameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
@Business("customerBusinss")
public class CustomerBusinessImpl implements CustomerBusiness {

    @Inject
    private CustomerMapper customerMapper;
    @Inject
    private PageRepository pageRepository;
    @Inject
    private JedisClient jedisClient;


    @Override
    public PageResult<CustomerBO> selectByPage(CustomerParam param) {
        Parameters.requireNotNull(param.getPage(), "分页查询参数page不能为空");
        Parameters.requireNotNull(param.getCount(),"分页查询参数count不能为空");
        PageResult<CustomerBO> pageResult  = pageRepository.selectPaging(CustomerMapper.class,"selectByPage",param);
        if(pageResult == null){
            return new PageResult<CustomerBO>(new ArrayList<>(0), 0);
        }
        return pageResult;
    }

    public static void main(String [] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class);
        CustomerBusiness customerBusiness = (CustomerBusinessImpl)context.getBean("customerBusiness");
//        System.out.println(myBusiness.getMyName());
//        System.out.println("fafsdsa");
        CustomerParam param = new CustomerParam();
//        param.setPage(1);
//        param.setCount(10);
        param.setName("余");
        System.out.println(JsonUtil.toJsonString(customerBusiness.selectByPage(param)));
    }
}
