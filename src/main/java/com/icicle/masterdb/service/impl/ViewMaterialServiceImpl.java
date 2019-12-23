package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.MaterialFabricMapper;
import com.icicle.masterdb.dao.masterdb.ViewMaterialAttributeMapper;
import com.icicle.masterdb.dao.masterdb.ViewMaterialMapper;
import com.icicle.masterdb.dao.masterdb.ViewSelectItemMapper;
import com.icicle.masterdb.model.MaterialFabric;
import com.icicle.masterdb.model.ViewMaterial;
import com.icicle.masterdb.model.ViewMaterialAttribute;
import com.icicle.masterdb.model.ViewSelectItem;
import com.icicle.masterdb.pojo.vo.MaterialAttributeVO;
import com.icicle.masterdb.pojo.vo.MaterialDetailVO;
import com.icicle.masterdb.pojo.vo.ViewMaterialAttributeVO;
import com.icicle.masterdb.pojo.vo.ViewMaterialDetailVO;
import com.icicle.masterdb.service.ViewMaterialService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;


/**
 * @author liurenhua
 */
@Service
public class ViewMaterialServiceImpl extends AbstractService<ViewMaterial> implements ViewMaterialService {
    /**
     * 小类为洗标的原材料属性code固定为 00530
     */
    private final String CODE = "00530";
    @Resource
    private ViewMaterialMapper viewMaterialMapper;
    @Resource
    private ViewSelectItemMapper viewSelectItemMapper;
    @Resource
    private ViewMaterialAttributeMapper viewMaterialAttributeMapper;
    @Resource
    private MaterialFabricMapper materialFabricMapper;

    @Override
    public DataTableRecord listMaterial(String sEcho, Integer pageIndex, Integer pageSize,
                                        String sSearch, Integer status,
                                        Integer sortCol, String sortDir) {
        sortDir = PageUtil.orderMethod(sortDir);
        String orderColoum = PageUtil.orderBy(sortCol, ViewMaterial.class);

        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ViewMaterial> viewMaterialList;
        Condition condition = new Condition(ViewMaterial.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andNotEqualTo("syncStatus", status);
        if (!StringUtils.isBlank(sSearch)) {
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.and(condition.createCriteria().andLike("materialCode", words).orLike("materialName", words));
        }
        if (ASC.equals(sortDir)) {
            condition.orderBy(orderColoum).asc().orderBy("lastSyncDate").desc();
        } else {
            condition.orderBy(orderColoum).desc().orderBy("lastSyncDate").desc();
        }
        try {
            viewMaterialList = viewMaterialMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(viewMaterialList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(viewMaterialList);
        return dataTableRecord;
    }

    @Override
    public MaterialDetailVO getMaterialDetailAndAttribute(String materialCode) {
        MaterialDetailVO materialDetailVO = new MaterialDetailVO();
        List<ViewMaterialAttribute> viewMaterialAttributeList;
        List<ViewMaterialAttributeVO> viewMaterialAttributeVOList;
        List<MaterialAttributeVO> materialAttributeValueList;
        ViewMaterialDetailVO viewMaterialDetailVO;
        List<ViewSelectItem> viewSelectItemList;
        try {
            viewMaterialAttributeList = viewMaterialAttributeMapper.findAttributeByCode(materialCode);
            if (viewMaterialAttributeList == null || viewMaterialAttributeList.size() == 0) {
                return null;
            }
            materialAttributeValueList = viewMaterialAttributeMapper.findAttributeValueByCode(materialCode, viewMaterialAttributeList);
            viewMaterialAttributeVOList = PojoConvertUtil.convertPojoList(viewMaterialAttributeList, ViewMaterialAttributeVO.class);
            viewMaterialDetailVO = viewMaterialMapper.findMateriaByCode(materialCode);
            List<String> ids = new ArrayList<>();
            if (viewMaterialAttributeList != null && viewMaterialAttributeList.size() != 0) {
                for (ViewMaterialAttributeVO viewMaterialAttributeVO : viewMaterialAttributeVOList) {
                    if (viewMaterialAttributeVO != null && viewMaterialAttributeVO.getHasItem() && viewMaterialAttributeVO.getItemSource() != null) {
                        ids.add(viewMaterialAttributeVO.getItemSource());
                    }
                }
            }
            //查找这个原材料有没有大属性 有的话这个属性必须加进去
            MaterialFabric materialFabric = materialFabricMapper.findMaterialFabricByCode(materialCode);
            if (materialFabric != null) {
                MaterialAttributeVO materialAttributeVO = new MaterialAttributeVO();
                materialAttributeVO.setId(0);
                materialAttributeVO.setAttrCode(CODE);
                materialAttributeVO.setAtrValue(materialFabric.getFabric());
                materialAttributeValueList.add(materialAttributeVO);
            }
            viewSelectItemList = viewSelectItemMapper.findSelectItem(ids);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        //下拉列表选项
        materialDetailVO.setViewSelectItemList(viewSelectItemList);
        //原材料属性（竖表）
        materialDetailVO.setViewMaterialAttributeVOList(viewMaterialAttributeVOList);
        //原材料基本属性（横表属性）
        materialDetailVO.setViewMaterialDetailVO(viewMaterialDetailVO);
        //原材料属性值
        materialDetailVO.setMaterialAttributeVOList(materialAttributeValueList);
        return materialDetailVO;
    }
}