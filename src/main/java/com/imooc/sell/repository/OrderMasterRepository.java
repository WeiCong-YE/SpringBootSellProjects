package com.imooc.sell.repository;

import com.imooc.sell.dataoobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单表
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 根据用户的openId,分页查询用户的订单信息
     *
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
