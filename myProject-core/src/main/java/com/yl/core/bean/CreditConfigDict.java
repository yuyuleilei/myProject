/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.bean;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

import java.io.Serializable;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/26
 * @description
 */
@Indexes({
        @Index(fields = {@Field("key")}, options = @IndexOptions(unique = true))
})
@Entity(value = "credit_config_dict", noClassnameStored = true)
public class CreditConfigDict extends CreditConfigBase implements Serializable {
    private static final long serialVersionUID = -450676920991666839L;
    @Id
    private ObjectId _id;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

}
