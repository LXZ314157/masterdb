package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.SubExpenditureCategory;
import com.icicle.masterdb.pojo.vo.SubExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 11:22:13.
*/
public interface SubExpenditureCategoryService extends Service<SubExpenditureCategory> {

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param buyerName
     * @param buyerState
     * @return
     */
    DataTableRecord getSubExList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState);


    /**
     *
     * @param subExcategoryId
     * @param lanCode
     * @return
     */
    SubExpenditureCategoryVO findsubExInfoBySubExcategoryId(String subExcategoryId,String lanCode);


    /**
     * 获取开支子类的同步列表
     * @param subExcategoryIdList
     * @param lanCode
     * @return
     */
    List<SyncPropertyVO> getSyncSubExCategoryInfo(String [] subExcategoryIdList,String lanCode);

    /**
     * 获取开支子类的导出列表
     * @param subExIdOrName
     * @param lanCode
     * @return
     */
    List<SubExpenditureCategoryVO> findByQueryCondition(String subExIdOrName,String lanCode);


    /**
     * 获取导出开支子类的workbook
     * @param list
     * @return
     */
    Workbook exportExExcel(List<SubExpenditureCategoryVO> list);

}