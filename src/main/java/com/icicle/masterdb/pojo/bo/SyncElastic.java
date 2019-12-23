package com.icicle.masterdb.pojo.bo;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.model.ViewAttriItem;
import com.icicle.masterdb.model.ViewSearchimagesElastic;

import java.util.List;

/**
 * @author liumingming
 * @version 2018-05-11 11:27.
 */
public class SyncElastic {

    private List<ViewAttriItem> attriItems;
    private List<ViewSearchimagesElastic> elasticList;

    public List<ViewAttriItem> getAttriItems() {
        return attriItems;
    }

    public void setAttriItems(List<ViewAttriItem> attriItems) {
        this.attriItems = attriItems;
    }

    public List<ViewSearchimagesElastic> getElasticList() {
        return elasticList;
    }

    public void setElasticList(List<ViewSearchimagesElastic> elasticList) {
        this.elasticList = elasticList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
