package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsShippingAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsShippingAddressMapper {

    /**
     * 插入收货地址
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
