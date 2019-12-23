package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ModelProductMappingMapper;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ModelProductMapping;
import com.icicle.masterdb.service.ModelProductMappingService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author liurenhua
 */
@Service
public class ModelProductMappingServiceImpl extends AbstractService<ModelProductMapping> implements ModelProductMappingService {

    private final int ROW_COUNT = 2;
    /**
     * 产品编号或者模特编号的最大以及最小长度
     */
    private final int MAX_CODE_LENGTH = 20;
    private final int MIN_CODE_LENGTH = 13;

    private final String UNDER_LINE = "_";
    @Resource
    private ModelProductMappingMapper modelProductMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ModelProductMapping> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {

        List<ModelProductMapping> modelProductMappingList = new ArrayList<>();
        String createdBy = SessionManager.getLoginName();

        //创建workbook对象
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        //获得总行数
        int rowNum = sheet.getLastRowNum() + 1;

        //从第1行开始解析excel数据
        for (int i = 1; i < rowNum; i++) {
            String[] colValues = ExcelUtil.parseRow(sheet.getRow(i), ROW_COUNT);
            if (valuesValidate(colValues)) {
                //如果在数据库中已经存在或者在当前excel中已经出现过的对应，则舍去
                if (exists(colValues[1], colValues[0], modelProductMappingList)) {
                    continue;
                }

                ModelProductMapping modelProductMapping = new ModelProductMapping();
                modelProductMapping.setModelCode(filterCode(colValues[0]));
                modelProductMapping.setProductCode(filterCode(colValues[1]));
                modelProductMapping.setCreatedDate(DateUtil.now());
                modelProductMapping.setCreatedBy(createdBy);
                modelProductMapping.setStatus(Integer.valueOf(1));
                modelProductMappingList.add(modelProductMapping);
            }
        }

        return modelProductMappingList;
    }

    /**
     * @param code 编码，这个函数在使用之前已经校验过code不为空，因此这里不用再去校验了
     *             <p>
     *             去除编码中带下划线之后的部分，
     *             如果不带下划线，则返回这个字符串本身
     */
    private String filterCode(String code) {
        return code.contains(UNDER_LINE) ? code.substring(0, code.lastIndexOf(UNDER_LINE)) : code;
    }

    /**
     * 判断某个模特产品的对应关系在list中是否存在
     *
     * @param productCode
     * @param modelCode
     * @return
     */
    private boolean exists(String productCode, String modelCode, List<ModelProductMapping> list) {
        if (StringUtils.isBlank(productCode) || StringUtils.isBlank(modelCode)) {
            return true;
        }
        if (ListUtil.isBlank(list)) {
            return false;
        }
        return list.stream().anyMatch(m -> Objects.equals(m.getProductCode(), productCode) &&
                Objects.equals(m.getModelCode(), modelCode));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "批量导入模特产品对应关系")
    public int saveList(List<ModelProductMapping> list) {
        int count = 0;
        if (ListUtil.isBlank(list)) {
            return count;
        }
        String userName = SessionManager.getLoginName();

        List<ModelProductMapping> existList = modelProductMapper.findMappingVO();

        List<ModelProductMapping> updateList = new ArrayList<>();
        List<ModelProductMapping> insertList = new ArrayList<>();

        for (ModelProductMapping m : list) {
            if (ListUtil.isBlank(existList)) {
                insertList.add(m);
            } else if (existList.stream().anyMatch(p -> Objects.equals(p.getProductCode(), m.getProductCode())
                    && Objects.equals(p.getModelCode(), m.getModelCode()))) {
                m.setLastUpdatedDate(DateUtil.now());
                m.setLastUpdatedBy(userName);
                updateList.add(m);
            } else {
                insertList.add(m);
            }
        }

        List<List<ModelProductMapping>> splitInsertList = ListUtil.splitArrayList(insertList, 50);
        if (!ListUtil.isBlank(splitInsertList)) {
            for (List<ModelProductMapping> productMappingList : splitInsertList) {

                if (ListUtil.isBlank(productMappingList)) {
                    continue;
                }

                List<String> distinctList = new ArrayList<>();

                productMappingList.stream().forEach(p -> {
                    if (!distinctList.contains(p.getProductCode())) {
                        distinctList.add(p.getProductCode());
                    }
                });

                List<String> validCodeList = productMapper.findLegalProductCode(distinctList);
                if (ListUtil.isBlank(validCodeList)) {
                    continue;
                }
                List<ModelProductMapping> validList = productMappingList.stream().filter(
                        p -> validCodeList.contains(p.getProductCode())).collect(Collectors.toList());
                if (ListUtil.isBlank(validList)) {
                    continue;
                }
                count += modelProductMapper.insertList(validList);
            }
        }

        List<List<ModelProductMapping>> splitUpdateList = ListUtil.splitArrayList(updateList, 50);
        if (!ListUtil.isBlank(splitUpdateList)) {
            for (List<ModelProductMapping> productMappingList : splitUpdateList) {
                count += modelProductMapper.updateList(productMappingList);
            }
        }
        return count;
    }

    @Override
    @LogAction(logDesc = "删除模特产品对应关系")
    public int deleteByMappingId(Integer mappingId) {
        if (mappingId == null) {
            return 0;
        }
        return modelProductMapper.deleteByMappingId(mappingId);
    }

    @Override
    @LogAction(logDesc = "更新模特产品对应关系")
    public int updateMapping(ModelProductMapping modelProductMapping) {
        if (modelProductMapping == null) {
            return 0;
        }
        return update(modelProductMapping);
    }

    /**
     * 对数组做一个简单的校验
     *
     * @param col
     * @return
     */
    private boolean valuesValidate(String[] col) {
        if (col == null || col.length < ROW_COUNT) {
            return false;
        }
        if (StringUtils.isBlank(col[0]) || StringUtils.isBlank(col[1])) {
            return false;
        }
        if (col[0].length() > MAX_CODE_LENGTH || col[0].length() < MIN_CODE_LENGTH) {
            return false;
        }
        if (col[1].length() > MAX_CODE_LENGTH || col[1].length() < MIN_CODE_LENGTH) {
            return false;
        }
        return true;
    }


}