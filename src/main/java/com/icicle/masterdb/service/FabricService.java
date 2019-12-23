package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Fabric;
import com.icicle.masterdb.pojo.vo.FabricVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author liurenhua
 */
public interface FabricService extends Service<Fabric> {
    /**
     * 返回Fabric的datatables格式的数据
     *
     * @param sEcho
     * @param pageIndex 当前页
     * @param pageSize  页大小
     * @param sSearch   搜索关键字
     * @param sortCol   派序列
     * @param sortDir   排序方式
     * @return
     */
    DataTableRecord createDataTables(String sEcho, Integer pageIndex, Integer pageSize,
                                     String sSearch, String sortDir, Integer sortCol);

    /**
     * 添加一个面料
     *
     * @param fabric
     * @return
     */
    int saveFabric(Fabric fabric);


    /**
     * 取出了原料的两列
     *
     * @return
     */
    List<Fabric> selectFabricPart();


    /**
     * * 解析excel文档，将其转换成fabric数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    List<Fabric> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException;


    /**
     * 更新面料信息
     *
     * @param fabric
     * @return
     */
    int updateFabric(Fabric fabric);


    /**
     * 批量存储面料
     *
     * @param list
     * @return
     */
    int saveList(List<Fabric> list);

    /**
     * 验证面料并返回面料名
     *
     * @param fabricCode
     * @return
     */
    Fabric searchFabric(String fabricCode);

    /**
     * 更新款号的面料编号
     *
     * @param fabric
     * @return
     */
    int updateProductFabric(Fabric fabric);

    /**
     * 获取面料列表
     * @param fabiricNameOrNo
     * @return
     */
    List<FabricVO> findByQueryCondition(String fabiricNameOrNo);

    /**
     * 获取面料workbook
     * @param list
     * @return
     */
    Workbook exportFabricExExcel(List<FabricVO> list);

}

