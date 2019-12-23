package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductLook;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductLookMapper extends MyMapper<ProductLook> {

    /**
     * 验证look序号
     *
     * @param lookId 序号
     * @return
     */
    Integer checkLookId(Integer lookId);

    /**
     * 批量验证look序号
     *
     * @param ids 序号
     * @return
     */
    List<Integer> checkLookIds(List<Integer> ids);

    /**
     * 验证look编号
     *
     * @param lookCode
     * @return
     */
    Integer checkLookCode(String lookCode);

    /**
     * 批量验证look编号
     *
     * @param codes
     * @return
     */
    List<String> checkLookCodes(List<String> codes);

    /**
     * 批量写入look数据
     *
     * @param list
     * @return
     */
    Integer bahctInsert(List<ProductLook> list);
}