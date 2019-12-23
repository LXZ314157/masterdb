package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.BuyerAccount;
import com.icicle.masterdb.pojo.vo.BuyerVO;
import com.icicle.masterdb.pojo.vo.SyncBuyerVO;
import com.icicle.masterdb.pojo.vo.SyncZoneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerMapper extends MyMapper<Buyer> {
    /**
     * 添加代理商信息
     *
     * @param buyer
     * @return
     */
    int saveBuyer(Buyer buyer);

    /**
     * 更新代理商信息
     *
     * @param buyer
     * @return
     */
    int updateBuyer(Buyer buyer);


    /**
     * 获取所有代理商的id，no，名称
     *
     * @return
     */
    List<Buyer> findBuyerVO();

    /**
     *  根据buyerNo查询店铺银行账户信息
     * @param buyerNo
     * @return
     */
    BuyerAccount findAccountByBuyerNo(Integer buyerNo);

    /**
     * 添加店铺银行账户信息
     * @return
     */
    int saveAccountByBuyerNo(BuyerAccount buyerAccount);

    /**
     * 更新店铺银行账户信息
     * @param buyerAccount
     * @return
     */
    int updateAccountByBuyerNo(BuyerAccount buyerAccount);

    /**
     * 更新buyerId
     * @param buyerId
     * @return
     */
    int updateBuyerId(@Param("buyerId") String buyerId, @Param("buyerNo") Integer buyerNo);

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
    List<BuyerVO> findByQueryCondition(@Param("buyerNameOrId") String buyerNameOrId,@Param("buyerState") String buyerState);

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