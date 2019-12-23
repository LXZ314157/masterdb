package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewMaterialAttribute;
import com.icicle.masterdb.pojo.vo.MaterialAttributeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author liurenhua
 */
public interface ViewMaterialAttributeMapper extends MyMapper<ViewMaterialAttribute> {

    /**
     * 根据物料编码获取该物料的所有属性
     * @param materialCode
     * @return
     */
    List<ViewMaterialAttribute> findAttributeByCode(String materialCode);

    /**
     *根据物料编码获取该物料的所有属性值
     * @param materialCode
     * @param list 需要哪些属性值  数据库中每条属性的属性值有一百多条  有些是不显示的
     * @return
     */
    List<MaterialAttributeVO> findAttributeValueByCode(@Param("materialCode") String materialCode,
                                                       @Param("list") List<ViewMaterialAttribute> list);
}