package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DtsAddressService {

    /**
     * 添加收货地址
     *
     * @param dtsAddress
     * @return
     */
    int insertdtsAddress(DtsAddress dtsAddress);

    /**
     * 查询收货地址
     *
     * @return
     */
    List<DtsAddress> selectdtsAddress();

    /**
     * 根据用户id查询收货地址
     *
     * @param userId
     * @return
     */
    List<DtsAddress> selectdtsAddressByUserId(Integer userId);

    /**
     * 修改收货地址
     *
     * @param dtsAddress
     * @return
     */
    int updatedtsAddress(DtsAddress dtsAddress);

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    boolean deleteDtsAddress(Integer id);

    /**
     * 模糊查询收货地址
     *
     * @param dtsAddress
     * @return
     */
    List<DtsAddress> selectdtsAddressed(DtsAddress dtsAddress);


}
