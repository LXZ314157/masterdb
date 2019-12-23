package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ProductLookMapper;
import com.icicle.masterdb.dao.masterdb.ViewProductAttributeSelectMapper;
import com.icicle.masterdb.dao.masterdb.ViewSelectItemMapper;
import com.icicle.masterdb.model.ProductLook;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.pojo.vo.LookSelectVO;
import com.icicle.masterdb.pojo.vo.ProductLookVO;
import com.icicle.masterdb.service.LookService;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liumingming
 * @version 2017-12-12 12:03.
 */
@Service
public class LookServiceImpl implements LookService {

    @Resource
    private ViewSelectItemMapper selectItemMapper;

    @Resource
    private ViewProductAttributeSelectMapper attributeSelectMapper;

    @Resource
    private ProductLookMapper productLookMapper;

    @Override
    public LookSelectVO getSelectData(String defCode) {
        LookSelectVO vo = new LookSelectVO();
        List<String> ids = new ArrayList<>();
        ids.add("select_line");
        ids.add("select_wave_band");
        ids.add("select_dev_season");
        try {
            vo.setSelectItemList(selectItemMapper.findSelectItem(ids));
            vo.setAttributeSelectList(attributeSelectMapper.findByDefindCode(defCode));
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return vo;
    }

    @Override
    public List<ViewProductAttributeSelect> getPositionList() {
        List<ViewProductAttributeSelect> list;
        try {
            list = attributeSelectMapper.findByDefindCode("position");
        } catch (Exception ex) {
            list = new ArrayList<>();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public Integer checkLookId(Integer lookId) {
        try {
            return productLookMapper.checkLookId(lookId);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return 1;
        }
    }

    @Override
    public Integer checkLookCode(String lookCode) {
        try {
            return productLookMapper.checkLookCode(lookCode);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return 1;
        }
    }

    @Override
    @LogAction(logDesc = "新增look")
    public int save(ProductLookVO vo) {
        try {
            ProductLook look = PojoConvertUtil.convertPojo(vo, ProductLook.class);
            if (null != look) {
                return productLookMapper.insertSelective(look);
            } else {
                return -1;
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "编辑look")
    public int update(ProductLookVO vo) {
        try {
            ProductLook look = PojoConvertUtil.convertPojo(vo, ProductLook.class);
            if (null != look) {
                return productLookMapper.updateByPrimaryKey(look);
            } else {
                return -1;
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return 0;
        }
    }

    @Override
    public DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, Integer lookId) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        List<ProductLook> list;
        Integer total;
        if (null != lookId) {
            list = new ArrayList<>();
            ProductLook look;
            try {
                look = productLookMapper.selectByPrimaryKey(lookId);
            } catch (Exception ex) {
                look = null;
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            if (null != look) {
                list.add(look);
            }
            total = Integer.valueOf(list.size());
            dataTableRecord.setAaData(list);
        } else {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            try {
                PageHelper.startPage(pageNum, pageSize);
                list = productLookMapper.selectAll();
            } catch (Exception ex) {
                list = new ArrayList<>();
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            PageInfo pageInfo = new PageInfo(list);
            total = (int) pageInfo.getTotal();
            dataTableRecord.setAaData(pageInfo.getList());
        }
        dataTableRecord.setITotalRecords(total);
        dataTableRecord.setITotalDisplayRecords(total);
        return dataTableRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "导入look")
    public int importLook(List<ProductLookVO> list) {
        if (null == list || list.size() == 0) {
            return 0;
        }
        int total = 0;
        int pageSize = 200;
        List<List<ProductLookVO>> lists;
        if (list.size() > pageSize) {
            lists = ListUtil.splitArrayList(list, pageSize);
        } else {
            lists = new ArrayList<>();
            lists.add(list);
        }
        int length = lists.size();
        for (int i = 0; i < length; i++) {
            List<Integer> ids = new ArrayList<>();
            List<String> codes = new ArrayList<>();
            for (ProductLookVO vo : lists.get(i)) {
                ids.add(vo.getLookId());
                codes.add(vo.getLookCode());
            }
            List<Integer> checkLookIds = productLookMapper.checkLookIds(ids);
            List<String> checkLookCodes = productLookMapper.checkLookCodes(codes);
            List<ProductLookVO> insertList;
            if (null == checkLookIds || checkLookIds.size() == 0) {
                insertList = lists.get(i);
            } else {
                insertList = lists.get(i).stream()
                        .filter(p -> !checkLookIds.contains(p.getLookId()) &&
                                !checkLookCodes.contains(p.getLookCode())).collect(Collectors.toList());
            }
            if (ListUtil.isBlank(insertList)) {
                continue;
            }
            total += productLookMapper.bahctInsert(PojoConvertUtil.convertPojoList(insertList, ProductLook.class));
        }
        return total;
    }
}