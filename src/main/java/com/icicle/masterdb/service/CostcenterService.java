package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Costcenter;
import com.icicle.masterdb.pojo.vo.CostcenterVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-14 13:27:38.
*/
public interface CostcenterService extends Service<Costcenter> {

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param buyerName
     * @param buyerState
     * @return
     */
    DataTableRecord getCostCenterList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState);

    /**
     *
     * @param list
     * @return
     */
    List<Costcenter> checkCostcenterZhs(List<Costcenter> list);

    /**
     * 获取同步到汇联易和EHR的成本中心
     * @param costcenterIdList
     * @param lanCode
     * @return
     */
    List<CostcenterVO> getSyncCostcenterInfo(String [] costcenterIdList,String lanCode);


    /**
     * 导出成本中心excel
     * @param list
     * @return
     */
    Workbook exportCostcenterExcel(List<CostcenterVO> list);

}