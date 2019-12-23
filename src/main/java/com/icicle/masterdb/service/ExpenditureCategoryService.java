package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.model.ExpenditureCategory;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.pojo.vo.ExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 10:24:39.
*/
public interface ExpenditureCategoryService extends Service<ExpenditureCategory> {
    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param buyerName
     * @param buyerState
     * @return
     */
    DataTableRecord getExList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState);


    ExpenditureCategoryVO findExInfoByExcategoryId(String excategoryId,String lanCode);


    /**
     * 获取同步到伯俊的开支类别列表
     * @param excategoryIdList
     * @param lanCode
     * @return
     */
    List<SyncPropertyVO> getSyncExCategoryInfo(String [] excategoryIdList,String lanCode);


    /**
     * 获取开支类别导出的excel
     * @param exIdOrName
     * @param lanCode
     * @return
     */
    List<ExpenditureCategoryVO> findByQueryCondition(String exIdOrName,String lanCode);


    /**
     * 获取开支类别导出excel
     * @param list
     * @return
     */
    Workbook exportExExcel(List<ExpenditureCategoryVO> list);


}