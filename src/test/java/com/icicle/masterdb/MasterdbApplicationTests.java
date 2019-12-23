package com.icicle.masterdb;

import com.icicle.masterdb.dao.masterdb.DynamicDataMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterdbApplicationTests {

    @Resource
    private DynamicDataMapper dynamicDataMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void tests(){
//        CodeGeneratorUtil codeGeneratorUtil = new CodeGeneratorUtil();
//        codeGeneratorUtil.genModelAndMapper("product_extend_attribute_item","ProductExtendAttributeItem");//根据表生成实体、mapper.java、mapper.xml
//        codeGeneratorUtil.genService("product_extend_attribute_item","ProductExtendAttributeItem");//根据表和实体生成service、serviceImpl
//        codeGeneratorUtil.genController("responsibilitycenter","Responsibilitycenter");//根据表和实体生成controller
    }

//    @Test
//    public void testAlterMapper() {
//        String tableName = "product_dimension_training";
//        DynamicColumn column = new DynamicColumn();
//        column.setColumnName("test");
//        column.setDataLength(0);
//        column.setDataType("INT");
//
//        int ret = dynamicDataMapper.alterTableAddColumn(tableName, column);
//        System.out.println("ret: " + ret);
//    }
//
//    @Test
//    public void testCreateMapper() {
//        String tableName = "product_dimension_training_2";
//        DynamicColumn column = new DynamicColumn();
//        column.setColumnName("test");
//        column.setDataLength(0);
//        column.setDataType("INT");
//
//        DynamicColumn column2 = new DynamicColumn();
//        column2.setColumnName("test_2");
//        column2.setDataLength(300);
//        column2.setDataType("VARCHAR");
//
//        List<DynamicColumn> columns = new ArrayList<>();
//        columns.add(column);
//        columns.add(column2);
//
//        int ret = dynamicDataMapper.createTable(tableName, columns);
//        System.out.println("ret: " + ret);
//
//        tableName = "product_dimension_merchandising";
//        ret = dynamicDataMapper.createTable(tableName, null);
//        System.out.println("ret: " + ret);
//    }

}