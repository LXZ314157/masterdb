package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.BuyerAccount;
import com.icicle.masterdb.pojo.vo.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
public interface BuyerService extends Service<Buyer> {
    /**
     * 插入代理商 数据库
     *
     * @param buyer
     * @return
     */
    int saveBuyer(Buyer buyer);

    /**
     * 更新代理商 数据库
     *
     * @param buyer
     * @return
     */
    int updateBuyer(Buyer buyer);

    /**
     * 新增代理商
     *
     * @param buyerVO
     * @return
     */
    BuyerVO insertBuyer(BuyerVO buyerVO);

    /**
     * 更新代理商
     *
     * @param buyerVO
     * @return
     */
    int updateBuyer(BuyerVO buyerVO);

    /**
     * 代理商属性共享数据
     *
     * @return
     */
    BuyerSharedDefVO getShareData();

    /**
     * 单条查询代理商基本信息
     *
     * @param buyerNo
     * @return
     */
    BuyerVO findByBuyerNo(Integer buyerNo);

    /**
     * 查询店铺的银行账户信息
     * @param buyerNo
     * @return
     */
    BuyerAccount findAccountByBuyerNo(Integer buyerNo);

    /**
     * 获取代理商类型，城市和区域
     *
     * @return
     */
    BuyerDropDownVO getDropDownList();

    /**
     * 加载代理商列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param buyerName
     * @param buyerState
     * @return
     */
    DataTableRecord getBuyerList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState);

    /**
     * 获取所有代理商的编号
     *
     * @return
     */
    List<SharedBuyerVO> findSharedBuyerList();


    /**
     * 查询buyerId是否存在
     * @param buyerId
     * @return
     */
    int checkBuyerId(String buyerId);


    /**
     * 查询buyer列表
     * @param buyerNameOrId
     * @param buyerState
     * @return
     */
    List<BuyerVO> findByQueryCondition(String buyerNameOrId,String buyerState);


    /**
     * 导出代理商列表
     * @param list
     * @return
     */
    Workbook exportBuyerExcel(List<BuyerVO> list);

    /**
     * 获取同步到FRID的代理商
     * @param buyerId
     * @return
     */
    SyncBuyerVO getSyncBuyerById(String buyerId);

    /**
     * 根据buyerId获取同步代理商
     * @param buyerId
     * @return
     */
    SyncBuyerVO getSyncBurgeonBuyerById(String buyerId);

    /**
     * 根据buyerId获取同步代理商--云学堂
     * @param buyerId
     * @return
     */
    SyncZoneVO getSyncYxtStoreByNo(String buyerId);


}