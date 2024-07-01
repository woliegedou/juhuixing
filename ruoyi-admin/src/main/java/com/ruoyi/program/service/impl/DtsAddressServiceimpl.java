package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsAddress;
import com.ruoyi.program.entity.Vo.DtsAddressVo;
import com.ruoyi.program.mapper.DtsAddressMapper;
import com.ruoyi.program.service.DtsAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DtsAddressServiceimpl implements DtsAddressService {
    @Autowired
    private DtsAddressMapper dtsAddressMapper;

    /**
     * 插入DtsAddress信息。
     * <p>
     * 本方法通过调用dtsAddressMapper的insertdtsAddress方法，来实现DtsAddress信息的插入操作。
     * 主要用于在数据库中新增DtsAddress记录。
     *
     * @param dtsAddress 待插入的DtsAddress对象，包含完整的DtsAddress信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertdtsAddress(DtsAddress dtsAddress) {
        return dtsAddressMapper.insertdtsAddress(dtsAddress);
    }

    /**
     * 根据提供的DtsAddress对象查询相应的地址信息。
     * 本方法通过调用dtsAddressMapper的selectdtsAddress方法来实现地址信息的查询。
     *
     * @return 返回匹配到的地址信息列表。如果未找到匹配的地址信息，则返回空列表。
     */
    @Override
    public List<DtsAddress> selectdtsAddress() {
        return dtsAddressMapper.selectdtsAddress();
    }

    @Override
    public List<DtsAddress> selectdtsAddressByUserId(Integer id) {
        return dtsAddressMapper.selectdtsAddressByUserId(id);
    }

    /**
     * 更新DtsAddress表中的数据。
     * <p>
     * 本方法通过调用dtsAddressMapper的updatedtsAddress方法，来实现对DtsAddress表中特定记录的更新操作。
     * 主要用于在系统中更新数据同步地址的相关信息，以确保数据同步配置的准确性。
     *
     * @param dtsAddress 包含需要更新的数据的DtsAddress对象。
     * @return 返回更新操作影响的行数，通常情况下，如果影响行数大于0，则表示更新成功。
     */
    @Override
    public int updatedtsAddress(DtsAddress dtsAddress) {
        return dtsAddressMapper.updatedtsAddress(dtsAddress);
    }

    /**
     * 删除DTS地址信息。
     *
     * @param id DTS地址的唯一标识符。
     * @return 如果删除成功，返回true；否则返回false。
     * @Override
     */
    public boolean deleteDtsAddress(Integer id) {
        // 调用dtsAddressMapper的deleteDtsAddress方法，传入ID参数，尝试删除DTS地址。
        // 返回值是删除操作影响的行数，如果大于0，则表示删除成功。
        return dtsAddressMapper.deleteDtsAddress(id) > 0;
    }


    /**
     * 根据地址名称查询DtsAddress对象列表。
     * 本方法通过调用dtsAddressMapper的selectdtsAddressed方法，查询与给定地址名称匹配的DtsAddress对象列表。
     * 主要用于在系统中根据地址名称进行地址数据的检索，以支持相关的业务操作。
     *
     * @param dtsAddress 地址名称，用于查询条件。
     * @return 返回匹配地址名称的DtsAddress对象列表。
     */
    @Override
    public List<DtsAddress> selectdtsAddressed(DtsAddress dtsAddress) {
        return dtsAddressMapper.selectdtsAddressed(dtsAddress);
    }

}
