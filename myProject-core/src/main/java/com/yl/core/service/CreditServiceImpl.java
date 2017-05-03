/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mongodb.client.MongoDatabase;
import com.yl.core.app.AppDataConfig;
import com.yl.core.app.WebAppConfig;
import com.yl.core.bean.CreditCode;
import com.yl.core.bean.CreditConfigBase;
import com.yl.core.bean.CreditConfigDict;
import com.yl.core.bean.CreditConfigParam;
import com.zhixindu.commons.page.PageParam;
import com.zhixindu.commons.page.PageResult;
import com.zhixindu.commons.utils.JsonUtil;
import com.zhixindu.commons.utils.Parameters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
@Service("creditService")
public class CreditServiceImpl implements CreditService {

    @Inject
    private Datastore datastore;
    @Inject
    private MongoDatabase mongoDatabase;
    @Inject
    private MongoDbService mongoDbService;

    @Override
    public CreditCode findByCode(String code) {
        return datastore.find(CreditCode.class).field("code").equal(code).get();
    }

    @Override
    public PageResult<CreditConfigBase> searchCreditConfigByPage(CreditConfigParam param) {
        Parameters.requireNotNull(param.getPage(), "分页查询参数page不能为空");
        Parameters.requireNotNull(param.getCount(),"分页查询参数count不能为空");
        Query<CreditConfigDict> query = datastore.find(CreditConfigDict.class);
        int page = param.getPage() > 0 ? param.getPage() : PageParam.DEFAULT_PAGE;
        int count = param.getCount() > 0 ? param.getCount() : PageParam.DEFAULT_COUNT;
        query.offset(count * page - count).limit(count);
        List<CreditConfigDict> creditConfigDictList= query.asList();
        if(CollectionUtils.isEmpty(creditConfigDictList)){
            return new PageResult<>();
        }
        List<CreditConfigBase> creditConfigBases = creditConfigDictList.stream().map(item ->{
            CreditConfigBase base = new CreditConfigBase();
            BeanUtils.copyProperties(item,base);
            return base;
        }).collect(Collectors.toList());
        PageResult<CreditConfigBase> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setCount(count);
        pageResult.setRows(creditConfigBases);
        int total = datastore.find(CreditConfigDict.class).asList().size();
        pageResult.setTotal(total);
        pageResult.setTotalPages(total % count == 0 ? total / count : total / count +1);
        pageResult.setFirst(page == 1);
        pageResult.setLast(page == pageResult.getTotalPages());
        return pageResult;
    }

    public static void main(String [] agars){
        ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class, AppDataConfig.class);
        CreditService creditService = (CreditServiceImpl)context.getBean("creditService");
        System.out.println(JsonUtil.toJsonString(creditService.findByCode("P101")));
        CreditConfigParam param = new CreditConfigParam();
        param.setPage(1);
        param.setCount(20);
        System.out.println(JsonUtil.toJsonString(creditService.searchCreditConfigByPage(param)));
    }
}
