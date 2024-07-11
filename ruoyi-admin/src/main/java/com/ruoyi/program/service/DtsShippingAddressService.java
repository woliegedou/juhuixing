package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsShippingAddress;

import java.util.List;

public interface DtsShippingAddressService {

    /**
     * 插入收货地址
     *
     * @param dtsShippingAddress
     * @return
     */
    int insertDtsShippingAddress(DtsShippingAddress dtsShippingAddress);
    /**
     * 更新收货地址
     * @param dtsShippingAddress
     * @return
     */
    int updateDtsShippingAddress(DtsShippingAddress dtsShippingAddress);

    /**
     * 查询收货地址
     * @param dtsShippingAddress
     * @return
     */
    List<DtsShippingAddress> selectDtsShippingAddress(DtsShippingAddress dtsShippingAddress);


}
