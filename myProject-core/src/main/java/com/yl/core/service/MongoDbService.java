/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.service;

import java.util.Map;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
public interface MongoDbService {
    void save(String collection, Map<String, Object> fieldValueMap);
    void update(String collection, Map<String,Object> paramMap, Map<String, Object> fieldValueMap);
}
