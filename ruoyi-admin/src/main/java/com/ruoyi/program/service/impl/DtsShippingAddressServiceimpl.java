package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsShippingAddress;
import com.ruoyi.program.mapper.DtsShippingAddressMapper;
import com.ruoyi.program.service.DtsShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtsShippingAddressServiceimpl implements DtsShippingAddressService {

    @Autowired
    private DtsShippingAddressMapper dtsShippingAddressMapper;

    /**
     * 插入DtsShippingAddress信息。
     * 本方法通过调用dtsShippingAddressMapper的insertDtsShippingAddress方法，将DtsShippingAddress对象插入数据库。
     * 主要用于在分布式事务中记录发货地址信息。
     *
     * @param dtsShippingAddress 待插入的发货地址对象，包含完整的发货地址信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertDtsShippingAddress(DtsShippingAddress dtsShippingAddress) {
        // 调用dtsShippingAddressMapper的insertDtsShippingAddress方法插入发货地址信息
        return dtsShippingAddressMapper.insertDtsShippingAddress(dtsShippingAddress);
    }

    /**
     * 更新DtsShippingAddress表中的配送地址信息。
     * <p>
     * 本方法通过调用dtsShippingAddressMapper的updateDtsShippingAddress方法，
     * 来更新数据库中对应的配送地址信息。方法接收一个DtsShippingAddress对象作为参数，
     * 该对象包含了需要更新的地址信息。方法返回值为int类型，表示更新操作影响的行数。
     *
     * @param dtsShippingAddress 包含了需要更新的配送地址信息的对象。
     * @return 返回更新操作影响的行数。
     */
    @Override
    public int updateDtsShippingAddress(DtsShippingAddress dtsShippingAddress) {
        return dtsShippingAddressMapper.updateDtsShippingAddress(dtsShippingAddress);
    }

    /**
     * 根据提供的DtsShippingAddress对象查询对应的配送地址信息。
     * 此方法通过调用dtsShippingAddressMapper的selectDtsShippingAddress方法来实现查询。
     * 如果提供的DtsShippingAddress对象包含特定的条件，则会根据这些条件查询满足要求的配送地址列表。
     * 如果对象为空或未设置任何条件，则可能会返回所有配送地址的列表，这取决于底层mapper的实现。
     *
     * @param dtsShippingAddress 查询条件对象，包含可能的筛选条件如收货人姓名、地址等。
     * @return 返回一个DtsShippingAddress类型的列表，包含所有匹配查询条件的配送地址。
     */
    @Override
    public List<DtsShippingAddress> selectDtsShippingAddress(DtsShippingAddress dtsShippingAddress) {
        return dtsShippingAddressMapper.selectDtsShippingAddress(dtsShippingAddress);
    }
}
