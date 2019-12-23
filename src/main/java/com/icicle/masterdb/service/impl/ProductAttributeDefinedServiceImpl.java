package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductAttributeDefinedMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductAttributeDefined;
import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductAttributeDefinedVO;
import com.icicle.masterdb.service.ProductAttributeDefinedService;
import com.icicle.masterdb.service.ProductAttributeItemService;
import com.icicle.masterdb.service.ProductDimensionAttributeService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyuling
 */
@Service
public class ProductAttributeDefinedServiceImpl extends AbstractService<ProductAttributeDefined> implements ProductAttributeDefinedService {
    @Resource
    private ProductAttributeDefinedMapper productAttributeDefinedMapper;
    @Resource
    private ProductAttributeItemService productAttributeItemService;
    @Resource
    private ProductDimensionAttributeService productDimensionAttributeService;

    /**
     * 添加产品选项的编码的最大值
     */
    public static final int ITEM_MAX = 1073741824;
    private final int MORE_ITEM = 0;
    private final int BASE_CODE = 2;
    private final String BIT = "bit";

    @Override
    public List<ProductAttributeDefinedVO> findAllAttributeDefinedVO() {
        List<ProductAttributeDefined> list = productAttributeDefinedMapper.findAllAttributeDefied();
        List<ProductAttributeDefinedVO> productAttributeDefinedVOList = PojoConvertUtil.convertPojoList(list, ProductAttributeDefinedVO.class);
        return productAttributeDefinedVOList;
    }

    @Override
    public List<ProductAttributeDefinedVO> findAllProductCategoryAttributeDefinedVO() {
        List<ProductAttributeDefined> list = productAttributeDefinedMapper.findAllProductCategoryAttributeDefined();
        List<ProductAttributeDefinedVO> productAttributeDefinedVOList = PojoConvertUtil.convertPojoList(list, ProductAttributeDefinedVO.class);
        return productAttributeDefinedVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "新增产品属性定义")
    public int addAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO) {
        ProductAttributeDefined productAttributeDefined = PojoConvertUtil.convertPojo(productAttributeDefinedVO, ProductAttributeDefined.class);
        List<ProductAttributeItem> itemList = productAttributeDefinedVO.getItemList();
        productAttributeDefined.setCreatedBy(SessionManager.getLoginName());
        productAttributeDefined.setCreationDate(DateUtil.now());
        int ret = productAttributeDefinedMapper.insertAll(productAttributeDefined);
        if (ret <= 0) {
            return 0;
        }
        int id = productAttributeDefined.getId();
        //bit 类型无选项添加
        if (productAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveItem(id, itemList);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "新增产品属性定义")
    public int addAttributeDefs(ProductAttributeDefinedVO productAttributeDefinedVO) {
        ProductAttributeDefined productAttributeDefined = PojoConvertUtil.convertPojo(productAttributeDefinedVO, ProductAttributeDefined.class);
        List<ProductAttributeItem> itemList = productAttributeDefinedVO.getItemList();
        productAttributeDefined.setCreatedBy(SessionManager.getLoginName());
        productAttributeDefined.setCreationDate(DateUtil.now());
        int ret = productAttributeDefinedMapper.insertAllProductCategoryAttri(productAttributeDefined);
        if (ret <= 0) {
            return 0;
        }
        int id = productAttributeDefined.getId();
        //bit 类型无选项添加
        if (productAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveItem(id, itemList);
        }
        return 1;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "更新属性和选项")
    public int updateAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO) {
        if (productAttributeDefinedVO == null) {
            return 0;
        }
        List<ProductAttributeItem> itemList = productAttributeDefinedVO.getItemList();
        ProductAttributeDefined productAttributeDefinedPojo = PojoConvertUtil.convertPojo(productAttributeDefinedVO, ProductAttributeDefined.class);
        productAttributeDefinedPojo.setLastUpdateDate(new Date());
        productAttributeDefinedPojo.setLastUpdatedBy(SessionManager.getLoginName());
        productAttributeDefinedMapper.updateDefindItem(productAttributeDefinedPojo);
        int id = productAttributeDefinedPojo.getId();
        if (productAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveItem(id, itemList);
        } else {
            dealItem(id);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "更新属性和选项")
    public int updateDimensionAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO) {
        if (productAttributeDefinedVO == null) {
            return 0;
        }
        List<ProductAttributeItem> itemList = productAttributeDefinedVO.getItemList();
        ProductAttributeDefined productAttributeDefinedPojo = PojoConvertUtil.convertPojo(productAttributeDefinedVO, ProductAttributeDefined.class);
        productAttributeDefinedPojo.setLastUpdateDate(new Date());
        productAttributeDefinedPojo.setLastUpdatedBy(SessionManager.getLoginName());
        productAttributeDefinedMapper.updateDimensionAttributeDef(productAttributeDefinedPojo);
        int id = productAttributeDefinedPojo.getId();
        if (productAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveItem(id, itemList);
        } else {
            dealItem(id);
        }
        return 1;
    }

    @Override
    public List<ProductAttributeItem> findItemById(Integer id) {
        Condition condition = new Condition(ProductAttributeItem.class);
        condition.createCriteria().andEqualTo("defId", id);
        try {
            return productAttributeItemService.findByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
    }



    /**
     * 执行的选项新增过程--维度
     *
     * @param id
     * @param itemList
     * @return
     */
    private int saveItem(Integer id, List<ProductAttributeItem> itemList) {
        int value = 1;
        if (ListUtil.isBlank(itemList)) {
            return 1;
        }
        List<ProductAttributeItem> updateList = new ArrayList<>();
        List<ProductAttributeItem> insertList = new ArrayList<>();
        for (ProductAttributeItem item : itemList) {
            item.setCode(value);
            value = BASE_CODE * value;
            if (value > ITEM_MAX) {
                return -1;
                // 最多添加30项 选项添加已超过最大限额，无法继续添加
            }
            item.setDefId(id);
            //新建
            if (item.getId() == 0) {
                item.setCreatedBy(SessionManager.getLoginName());
                item.setCreationDate(DateUtil.now());
                item.setStatus(true);
                insertList.add(item);
            } else {
                item.setLastUpdatedBy(SessionManager.getLoginName());
                item.setLastUpdateDate(DateUtil.now());
                updateList.add(item);
            }
        }
        int ret = dealAttributeItem(updateList, insertList);
        if (ret > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 产品无选项更改状态为无效
     *
     * @param id
     * @return
     */
    private int dealItem(Integer id) {
        Condition condition = new Condition(ProductAttributeItem.class);
        condition.createCriteria().andEqualTo("defId", id);
        List<ProductAttributeItem> exeits = productAttributeItemService.findByCondition(condition);
        if (ListUtil.isBlank(exeits)) {
            return 1;
        }
        for (ProductAttributeItem item : exeits) {
            item.setStatus(false);
            item.setLastUpdateDate(DateUtil.now());
            item.setLastUpdatedBy(SessionManager.getLoginName());
        }

        int ret = productAttributeItemService.updateDimensionItem(exeits);
        if (ret > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 产品属性选项存在更新，不存在插入
     *
     * @param updateList
     * @param insertList
     * @return
     */
    private int dealAttributeItem(List<ProductAttributeItem> updateList, List<ProductAttributeItem> insertList) {
        if (updateList != null && updateList.size() > 0) {
            productAttributeItemService.updateDimensionItem(updateList);
        }
        if (insertList != null && insertList.size() > 0) {
            productAttributeItemService.save(insertList);
        }
        return 1;
    }


}