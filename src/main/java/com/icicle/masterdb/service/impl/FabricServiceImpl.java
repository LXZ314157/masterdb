

package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.FabricMapper;
import com.icicle.masterdb.dao.masterdb.ProductAttributeExtendMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Fabric;
import com.icicle.masterdb.pojo.vo.FabricVO;
import com.icicle.masterdb.service.FabricService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;


/**
 * 表格导入时，导入的两列分别为第一列 fabircCode和 第二列fabricName
 *
 * @author liurenhua
 */
@Service
public class FabricServiceImpl extends AbstractService<Fabric> implements FabricService {
    @Resource
    private FabricMapper fabricMapper;
    @Resource
    private ProductAttributeExtendMapper productAttributeExtendMapper;

    private final int ROW_COUNT = 2;
    /**
     * 每SPLIT个数据为一组插入到数据库中
     */
    private final int SPLIT_NUM = 50;
    private final int MAX_LENGTH = 30;

    @Override
    public List<Fabric> selectFabricPart() {
        return fabricMapper.selectFabricPart();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Fabric> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        //查询出数据库中当前已经存在的code
        //成功导入的数量
        List<Fabric> fabricList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        //获取sheet
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        if (ExcelUtil.parseRow(sheet.getRow(0), ROW_COUNT) == null) {
            return null;
        }
        //获得总行数
        int rowNum = sheet.getLastRowNum() + 1;

        for (int i = 1; i < rowNum; i++) {
            String[] colValues = ExcelUtil.parseRow(sheet.getRow(i), ROW_COUNT);
            if (colValues == null || colValues.length != ROW_COUNT) {
                continue;
            }

            //如果已经存在过的，排除
            if (valuesValidate(colValues)) {
                Fabric fabric = new Fabric();
                fabric.setFabricCode(colValues[0]);
                fabric.setFabricName(colValues[1]);
                //导入的表格中
                if (!codeExists(colValues[0], fabricList)) {
                    fabricList.add(fabric);
                }
            }
        }
        if (ListUtil.isBlank(fabricList)) {
            return null;
        }

        for (Fabric fabric : fabricList) {
            if (fabric == null) {
                continue;
            }

            fabric.setHasFeature(false);
            fabric.setCreatedDate(DateUtil.now());
            fabric.setCreatedBy(SessionManager.getLoginName());
            fabric.setLanguage(SessionManager.getLanguage());
            fabric.setStatus(1);
        }
        return fabricList;

    }

    @Override
    @LogAction(logDesc = "更新面料")
    public int updateFabric(Fabric fabric) {
        return fabricMapper.updateByPrimaryKeySelective(fabric);
    }

    @Override
    @LogAction(logDesc = "批量导入面料")
    @Transactional(rollbackFor = Exception.class)
    public int saveList(List<Fabric> fabricList) {
        int count = 0;
        int upCount = 0;

        List<List<Fabric>> splitList = ListUtil.splitArrayList(fabricList, SPLIT_NUM);
        if (!ListUtil.isBlank(splitList)) {
            for (List<Fabric> list : splitList) {

                if (ListUtil.isBlank(list)) {
                    continue;
                }

                List<String> existCodeList = fabricMapper.checkFabricCode(list);

                List<Fabric> updateList;
                List<Fabric> insertList;

                if (ListUtil.isBlank(existCodeList)) {
                    insertList = list;
                    updateList = null;
                } else {
                    updateList = list.stream().filter(f -> existCodeList.contains(f.getFabricCode())).collect(Collectors.toList());
                    insertList = list.stream().filter(f -> !existCodeList.contains(f.getFabricCode())).collect(Collectors.toList());
                }
                if (!ListUtil.isBlank(insertList)) {
                    count += fabricMapper.insertList(insertList);
                }
                if (!ListUtil.isBlank(updateList)) {
                    fabricMapper.batchUpdateFabric(updateList);
                    productAttributeExtendMapper.batchUpdateFabric(updateList);
                    upCount++;
                }
            }
        }
        if (count == 0 && upCount > 0) {
            return -1;
        }
        return count;
    }

    private boolean valuesValidate(String[] colValues) {

        if (colValues == null || colValues.length < ROW_COUNT) {
            return false;
        }

        if (colValues[0] == null || colValues[1] == null) {
            return false;
        }

        if (colValues[0].length() > MAX_LENGTH || colValues[1].length() > MAX_LENGTH) {
            return false;
        }

        return true;
    }

    /**
     * 验证code在list中是否包含
     *
     * @param code
     * @param list
     * @return
     */
    private boolean codeExists(String code, List<Fabric> list) {
        if (ListUtil.isBlank(list)) {
            return false;
        }
        return list.stream().anyMatch(f -> Objects.equals(code, f.getFabricCode()));
    }

    @Override
    public DataTableRecord createDataTables(String sEcho, Integer pageIndex, Integer pageSize,
                                            String sSearch, String sortDir, Integer sortCol) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Fabric> fabricList;
        String orderBy = PageUtil.orderBy(sortCol, Fabric.class);
        String orderMethod = PageUtil.orderMethod(sortDir);
        try {
            Condition condition = new Condition(Fabric.class);
            if (!StringUtils.isBlank(sSearch)) {
                String words = StringUtil.gsFormat("%{0}%", sSearch);
                condition.and(condition.createCriteria().andLike("fabricCode", words).orLike("fabricName", words));
            }
            if (ASC.equals(orderMethod)) {
                condition.orderBy(orderBy).asc();
            } else {
                condition.orderBy(orderBy).desc();
            }
            fabricList = fabricMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(fabricList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(fabricList);
        return dataTableRecord;
    }


    @Override
    @LogAction(logDesc = "新增面料")
    public int saveFabric(Fabric fabric) {
        return fabricMapper.saveFabric(fabric);
    }

    @Override
    public Fabric searchFabric(String fabricCode) {
        return fabricMapper.searchFabric(fabricCode);
    }


    @Override
    @LogAction(logDesc = "更新款号面料")
    public int updateProductFabric(Fabric fabric) {
        List<Fabric> updateList = new ArrayList<>();
        updateList.add(fabric);
        productAttributeExtendMapper.batchUpdateFabric(updateList);
        return 1;
    }

    @Override
    public List<FabricVO> findByQueryCondition(String fabiricNameOrNo) {
        return fabricMapper.findByQueryCondition(fabiricNameOrNo);
    }

    @Override
    public Workbook exportFabricExExcel(List<FabricVO> list) {
        return ExcelUtil.getFabiricWorkbook(list);
    }
}