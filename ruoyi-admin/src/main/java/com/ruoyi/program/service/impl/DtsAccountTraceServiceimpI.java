package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsAccountTrace;
import com.ruoyi.program.mapper.DtsAccountTraceMapper;
import com.ruoyi.program.service.DtsAccountTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsAccountTraceServiceimpI implements DtsAccountTraceService {
    @Autowired
    private DtsAccountTraceMapper dtsAccountTraceMapper;

    /**
     * 根据示例查询账号轨迹记录的数量。
     * 本方法通过调用DtsAccountTraceMapper接口的countByExample方法，实现根据提供的示例对象查询符合该示例条件的账号轨迹记录总数。
     * 主要用于统计或分页查询时，需要获取特定条件下的记录数量。
     *
     * @param example 账号轨迹记录的示例对象，包含了查询条件的属性及其对应的值，用于构建查询条件。
     * @return 符合示例条件的账号轨迹记录的数量。
     */


    @Override
    public List<DtsAccountTrace> countByExample(DtsAccountTrace example) {
        return dtsAccountTraceMapper.countByExample(example);
    }

    /**
     * 根据例子删除DtsAccountTrace数据。
     * 此方法通过调用dtsAccountTraceMapper的deleteByExample方法来实现数据的删除。
     * 它接受一个DtsAccountTraceMapper类型的参数，该参数定义了删除操作所依据的条件。
     * 返回值为删除操作影响的行数。
     *
     * @param example DtsAccountTraceMapper实例，用于定义删除操作的条件。
     * @return 删除操作影响的行数。
     */
    @Override
    public int deleteByExample(DtsAccountTrace example) {
        return this.dtsAccountTraceMapper.deleteByExample(example);
    }

    /**
     * 根据主键删除账号轨迹记录。
     *
     * @param id 账号轨迹记录的主键ID。
     * @return 删除操作影响的行数。
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        // 调用Mapper接口方法，根据主键ID删除账号轨迹记录
        return dtsAccountTraceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入DTS账号轨迹记录。
     * <p>
     * 本方法用于将DtsAccountTraceMapper实例插入到数据库中，以此记录DTS账号的相关操作轨迹。
     * 通过调用dtsAccountTraceMapper的insertDtsAccountTrace方法实现具体的插入操作。
     *
     * @param record 待插入的DtsAccountTraceMapper实例，包含账号操作的轨迹信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public boolean insertDtsAccountTrace(DtsAccountTrace record) {
        return dtsAccountTraceMapper.insertDtsAccountTrace(record);
    }

    /**
     * 通过调用DtsAccountTraceMapper的insertSelective方法，插入一条记录。
     * 此方法用于在数据库中插入一个DtsAccountTrace对象，但只插入非空的字段。
     * 选择性插入可以避免因插入大量空值而造成的性能低下。
     *
     * @param record 要插入的DtsAccountTrace对象
     * @return 插入操作影响的行数
     */
    @Override
    public int insertSelective(DtsAccountTrace record) {
        return dtsAccountTraceMapper.insertSelective(record);
    }

    /**
     * 根据例子查询DtsAccountTraceMapper实体的列表。
     *
     * @param example 查询的例子，用于指定查询条件。
     * @return 返回匹配条件的DtsAccountTraceMapper实体列表。如果查询结果为空，则返回空列表。
     */
    @Override
    public List<DtsAccountTrace> selectByExample(DtsAccountTrace example) {
        try {
            // 尝试通过例子查询DtsAccountTraceMapper实体列表
            List<DtsAccountTrace> dtsAccountTraceMappers = dtsAccountTraceMapper.selectByExample(example);
            // 如果查询结果为空，直接返回空列表
            if (dtsAccountTraceMappers.isEmpty()) {
                return Collections.emptyList();
            } else {
                // 如果查询结果不为空，返回查询结果
                return dtsAccountTraceMappers;
            }
        } catch (Exception e) {
            // 捕获查询过程中可能出现的异常，打印异常信息，并返回空列表
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 根据示例条件更新DtsAccountTraceMapper表中的记录。
     * 此方法利用MyBatis的动态SQL功能，根据提供的example对象条件更新相应的记录。
     * 它允许选择性地更新某些字段，提高了数据库操作的灵活性和效率。
     *
     * @param record  要更新的数据记录对象，包含要修改的字段及其新值。
     * @param example 更新条件的对象，用于指定更新操作的条件。可以利用此对象的属性和设置的条件，动态地构建SQL语句中的WHERE子句。
     * @return 返回影响的行数，即被更新的记录数。
     */
    @Override
    public int updateByExampleSelective(DtsAccountTrace record, DtsAccountTrace example) {
        return dtsAccountTraceMapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据示例更新DtsAccountTraceMapper表中的数据。
     * 此方法通过提供一个具体的更新记录和一个更新条件的示例，来实现对数据库中满足条件的记录进行更新。
     *
     * @param record  需要更新的数据记录，包含了要修改的字段及其新值。
     * @param example 更新条件的示例，用于指定哪些记录应该被更新。可以通过设置这个示例的字段及其条件来筛选待更新的记录。
     * @return 返回影响的行数，即被更新的记录数。
     */
    @Override
    public int updateByExample(DtsAccountTrace record, DtsAccountTrace example) {
        // 调用dtsAccountTraceMapper的updateByExample方法，执行根据示例的更新操作。
        return dtsAccountTraceMapper.updateByExample(record, example);
    }

    /**
     * 根据主键选择性更新DtsAccountTraceMapper记录。
     * 此方法覆盖了父类中的方法，实现了对DtsAccountTraceMapper记录的更新操作。
     * 它通过调用dtsAccountTraceMapper的updateByPrimaryKeySelective方法来实现更新。
     *
     * @param record 要更新的DtsAccountTraceMapper记录，只更新非空字段。
     * @return 返回影响的行数。
     */
    @Override
    public int updateByPrimaryKeySelective(DtsAccountTrace record) {
        return dtsAccountTraceMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新DtsAccountTraceMapper记录。
     *
     * @param record 要更新的数据记录，包含更新后的信息。
     * @return 返回更新操作影响的行数。
     */
    @Override
    public int updateByPrimaryKey(DtsAccountTrace record) {
        // 调用dtsAccountTraceMapper的updateByPrimaryKey方法，更新数据库中对应的记录
        return dtsAccountTraceMapper.updateByPrimaryKey(record);
    }
}
