package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DtsAddressMapper {
    /**
     * 添加收货地址
     *
     * @param dtsAddress
     * @return
     */
    int insertdtsAddress(DtsAddress dtsAddress);

    /**
     * 修改收货地址
     *
     * @param dtsAddress
     * @return
     */
    int updatedtsAddress(DtsAddress dtsAddress);

    /**
     * 查询收货地址
     * <p>
     * * @return
     */
    List<DtsAddress> selectdtsAddress();

    /**
     * 根据用户id查询收货地址
     *
     * @param id
     * @return
     */
    List<DtsAddress> selectdtsAddressByUserId(Integer id);

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    int deleteDtsAddress(Integer id);

    List<DtsAddress> selectdtsAddressed(DtsAddress dtsAddress);



}
