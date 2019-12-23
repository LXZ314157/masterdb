package com.icicle.masterdb.pojo.vo;

public class SyncSizeGroupVO {


    private String description;

    private String size_group_id;

    private String size_group_name;


    public SyncSizeGroupVO setSize_group_id (String size_group_id){
        this.size_group_id = size_group_id;
        return this;
    }

    public SyncSizeGroupVO setSize_group_name (String size_group_name){
        this.size_group_name = size_group_name;
        return this;
    }

    public SyncSizeGroupVO setDescription (String description){
        this.description = description;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public String getSize_group_id() {
        return size_group_id;
    }

    public String getSize_group_name() {
        return size_group_name;
    }
}
