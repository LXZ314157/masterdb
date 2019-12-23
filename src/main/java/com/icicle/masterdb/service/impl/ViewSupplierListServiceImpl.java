package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewSupplierListMapper;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.model.ViewSupplierList;
import com.icicle.masterdb.pojo.vo.StaffVO;
import com.icicle.masterdb.pojo.vo.SupplierVO;
import com.icicle.masterdb.service.ViewSupplierListService;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-09 21:26:32.
*/
@Service
public class ViewSupplierListServiceImpl extends AbstractService<ViewSupplierList> implements ViewSupplierListService {
    @Resource
    private ViewSupplierListMapper viewSupplierListMapper;

    @Override
    public SupplierVO findSupplierInfoBySupplierCode(String supplierCode) {
        Supplier supplier = viewSupplierListMapper.findSupplierInfoBySupplierCode(supplierCode);
        SupplierVO supplierVO = null;
        if(supplier!=null){
            supplierVO = PojoConvertUtil.convertPojo(supplier,SupplierVO.class);
            if(supplierVO.getSupplierDataStatus()==1){
                supplierVO.setSupplierDataStatusName("有效");
            }else{
                supplierVO.setSupplierDataStatusName("无效");
            }
        }
        return supplierVO;
    }

    @Override
    public List<SupplierVO> findByQueryCondition(String supplierCodeOrName, String supplierStatus) {
        List<SupplierVO> supplierVOList = null;
        List<Supplier> supplierList = viewSupplierListMapper.findByQueryCondition(supplierCodeOrName,supplierStatus);
        if(!ListUtil.isBlank(supplierList)){
            supplierVOList = PojoConvertUtil.convertPojoList(supplierList,SupplierVO.class);
            for(SupplierVO supplierVO : supplierVOList){
                if(supplierVO.getSupplierDataStatus()==1){
                    supplierVO.setSupplierDataStatusName("有效");
                }else{
                    supplierVO.setSupplierDataStatusName("无效");
                }
            }

        }
        return supplierVOList;
    }
}