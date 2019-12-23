package com.icicle.masterdb.service;

        import com.icicle.masterdb.core.DataTableRecord;
        import com.icicle.masterdb.model.Responsibilitycenter;
        import com.icicle.masterdb.core.Service;
        import com.icicle.masterdb.pojo.vo.ExpenditureCategoryVO;
        import com.icicle.masterdb.pojo.vo.RespCenterVO;
        import org.apache.poi.ss.usermodel.Workbook;

        import java.util.List;

/**
 * @author  CodeGeneratorUtil
 * @version 2019-01-14 10:19:33.
 */
public interface ResponsibilitycenterService extends Service<Responsibilitycenter> {

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param buyerName
     * @param buyerState
     * @return
     */
    DataTableRecord getRespList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState);

    /**
     *
     * @param responsibilitycenters
     * @return
     */
    List<Responsibilitycenter> checkRespcenterZhs(List<Responsibilitycenter> responsibilitycenters);


    /**
     * 获取责任中心对象
     * @param respcenterId
     * @param lanCode
     * @return
     */
    RespCenterVO findRespcenterInfoByRespcenterId(String respcenterId,String lanCode);


    /**
     * 获取责任中心导出excel的集合
     * @param respcenterIdOrName
     * @param lanCode
     * @return
     */
    List<RespCenterVO> findByQueryCondition(String respcenterIdOrName,String lanCode);


    /**
     * 获取责任中心的workbook
     * @param list
     * @return
     */
    Workbook exportRespcenterExcel(List<RespCenterVO> list);


}