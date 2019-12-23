package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewMaterial;
import com.icicle.masterdb.pojo.vo.MaterialDetailVO;

/**
 * Created by CodeGeneratorUtil on 2017-11-10 11:29:35.
 * @author liurenhua
 */
public interface ViewMaterialService extends Service<ViewMaterial> {

    /**
     * 获取原材料列表的datatable形式，为适应前端的datatables 控件
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch 模糊检索的关键字
     * @param type  查询同步状态不是type的数据 反映到数据库中就是加了一个查询条件 status != #{*} 如果不限状态 可传 -1
     * @param sortCol   排序列  在这里只能为1 ~ 8 之间的整数 如果不满足这个条件  默认为1
     * @param sortDir   排序规则 只能是desc 和asc  如果不是  我们默认为asc
     * @return
     */
    DataTableRecord listMaterial(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, Integer type,
                                 Integer sortCol, String sortDir);

    /**
     * 获取原材料的详细信息  其中包含 原材料的各种基本属性，拓展属性，属性值  动态展示在页面
     * @param materialCode 原材料编码
     * @return
     */
    MaterialDetailVO getMaterialDetailAndAttribute(String materialCode);

}