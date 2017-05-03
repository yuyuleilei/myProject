/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.zhixindu.commons.api.ServiceCode;
import com.zhixindu.commons.api.ServiceException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/25
 * @description
 */
@Service("mongoDbService")
public class MongoDbServiceImpl implements MongoDbService {

    @Inject
    private Datastore datastore;
    @Inject
    private MongoDatabase mongoDatabase;

    @Override
    public void update(String collection, Map<String,Object> paramMap, Map<String, Object> fieldValueMap) {
        if(StringUtils.isBlank(collection) || MapUtils.isEmpty(paramMap) || MapUtils.isEmpty(fieldValueMap)) {
            throw new ServiceException(ServiceCode.ILLEGAL_PARAM,"collection or paramMap or fieldValueMap is blank");
        }
        BasicDBObject query = new BasicDBObject(paramMap);
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject(fieldValueMap));
        mongoDatabase.getCollection(collection).updateMany(query, newDocument);
    }

    @Override
    public void save(String collection, Map<String, Object> fieldValueMap) {
        mongoDatabase.getCollection(collection).insertOne(new Document(fieldValueMap));
    }
}
