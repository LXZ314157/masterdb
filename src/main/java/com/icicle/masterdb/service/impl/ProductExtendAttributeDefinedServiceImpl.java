package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.dao.masterdb.ProductAttributeDefinedMapper;
import com.icicle.masterdb.dao.masterdb.ProductExtendAttributeDefinedMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductAttributeDefined;
import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.model.ProductExtendAttributeDefined;
import com.icicle.masterdb.model.ProductExtendAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductAttributeDefinedVO;
import com.icicle.masterdb.pojo.vo.ProductExtendAttributeDefinedVO;
import com.icicle.masterdb.service.ProductAttributeItemService;
import com.icicle.masterdb.service.ProductExtendAttributeDefinedService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ProductExtendAttributeItemService;
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
* @author  CodeGeneratorUtil
* @version 2019-07-09 17:04:31.
*/
@Service
public class ProductExtendAttributeDefinedServiceImpl extends AbstractService<ProductExtendAttributeDefined> implements ProductExtendAttributeDefinedService {
    @Resource
    private ProductExtendAttributeDefinedMapper productExtendAttributeDefinedMapper;

    @Resource
    private ProductAttributeItemService productAttributeItemService;
    @Resource
    private ProductExtendAttributeItemService productExtendAttributeItemService;
    @Resource
    private ProductAttributeDefinedMapper productAttributeDefinedMapper;


    /**
     * 添加产品选项的编码的最大值
     */
    public static final int ITEM_MAX = 1073741824;
    private final int MORE_ITEM = 0;
    private final int BASE_CODE = 2;
    private final String BIT = "bit";


    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "新增产品属性定义")
    public int addAttributeDef(ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO) {
        ProductExtendAttributeDefined productExtendAttributeDefined = PojoConvertUtil.convertPojo(productExtendAttributeDefinedVO, ProductExtendAttributeDefined.class);
        List<ProductExtendAttributeItem> itemList = productExtendAttributeDefinedVO.getItemList();
        productExtendAttributeDefined.setCreatedBy(SessionManager.getLoginName());
        productExtendAttributeDefined.setCreationDate(DateUtil.now());
        int ret = productExtendAttributeDefinedMapper.insertAllProductCategoryAttri(productExtendAttributeDefined);
        if (ret <= 0) {
            return 0;
        }
        int id = productExtendAttributeDefined.getId();
        //bit 类型无选项添加
        if (productExtendAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productExtendAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveProductAttrItem(id, itemList,productExtendAttributeDefined.getHasItem());
        }
        return 1;
    }

    @Override
    public List<ProductExtendAttributeDefinedVO> findAllProductCategoryAttributeDefinedVO() {
        return productExtendAttributeDefinedMapper.findAllProductCategoryAttributeDefinedVO();
    }


    @Override
    public List<ProductExtendAttributeItem> findItemById(Integer id) {
        Condition condition = new Condition(ProductAttributeItem.class);
        condition.createCriteria().andEqualTo("defId", id);
        try {
            return productExtendAttributeItemService.findByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
    }





    /**
     * 执行的选项新增过程--产品类别
     *
     * @param id
     * @param itemList
     * @return
     */
    private int saveProductAttrItem(Integer id, List<ProductExtendAttributeItem> itemList,Integer hasItem) {
        int value = 1;
        if (ListUtil.isBlank(itemList)) {
            return 1;
        }
        List<ProductExtendAttributeItem> updateList = new ArrayList<>();
        List<ProductExtendAttributeItem> insertList = new ArrayList<>();
        for (ProductExtendAttributeItem item : itemList) {
            if(hasItem==2){
                item.setCode(value);
                value = BASE_CODE * value;
                if (value > ITEM_MAX) {
                    return -1;
                    // 最多添加30项 选项添加已超过最大限额，无法继续添加
                }
            }
            item.setDefId(id);
            //新建
            if (item.getId() == 0 || hasItem ==1) {
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
     * 产品属性选项存在更新，不存在插入
     *
     * @param updateList
     * @param insertList
     * @return
     */
    private int dealAttributeItem(List<ProductExtendAttributeItem> updateList, List<ProductExtendAttributeItem> insertList) {
        if (updateList != null && updateList.size() > 0) {
            productExtendAttributeItemService.updateProductExtendItem(updateList);
        }
        if (insertList != null && insertList.size() > 0) {
            productExtendAttributeItemService.save(insertList);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "更新属性和选项")
    public int updateAttributeDef(ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO) {
        if (productExtendAttributeDefinedVO == null) {
            return 0;
        }
        List<ProductExtendAttributeItem> itemList = productExtendAttributeDefinedVO.getItemList();
        ProductExtendAttributeDefined productExtendAttributeDefined = PojoConvertUtil.convertPojo(productExtendAttributeDefinedVO, ProductExtendAttributeDefined.class);
        productExtendAttributeDefined.setLastUpdateDate(new Date());
        productExtendAttributeDefined.setLastUpdatedBy(SessionManager.getLoginName());
        productExtendAttributeDefinedMapper.updateDefindItem(productExtendAttributeDefined);
        int id = productExtendAttributeDefined.getId();
        if (productExtendAttributeDefinedVO.getHasItem() != MORE_ITEM && !(BIT).equals(productExtendAttributeDefinedVO.getDefType()) && itemList != null && itemList.size() > 0) {
            saveItem(id, itemList,productExtendAttributeDefined.getHasItem());
        } else {
            dealItem(id);
        }
        return 1;
    }


    /**
     * 执行的选项新增过程--维度
     *
     * @param id
     * @param itemList
     * @return
     */
    private int saveItem(Integer id, List<ProductExtendAttributeItem> itemList,Integer hasItem) {
        int value = 1;
        if (ListUtil.isBlank(itemList)) {
            return 1;
        }
        List<ProductExtendAttributeItem> updateList = new ArrayList<>();
        List<ProductExtendAttributeItem> insertList = new ArrayList<>();
        for (ProductExtendAttributeItem item : itemList) {
            if(hasItem==2){
                item.setCode(value);
                value = BASE_CODE * value;
                if (value > ITEM_MAX) {
                    return -1;
                    // 最多添加30项 选项添加已超过最大限额，无法继续添加
                }
            }
            //新建
            if (item.getId() == 0 || item.getDefId()==null) {
                item.setCreatedBy(SessionManager.getLoginName());
                item.setCreationDate(DateUtil.now());
                item.setStatus(true);
                insertList.add(item);
            } else {
                item.setLastUpdatedBy(SessionManager.getLoginName());
                item.setLastUpdateDate(DateUtil.now());
                updateList.add(item);
            }
            item.setDefId(id);
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

}