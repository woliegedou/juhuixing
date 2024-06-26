package com.ruoyi.program.controller;

import com.ruoyi.program.entity.DTO.DtsAccountTraceDto;
import com.ruoyi.program.entity.DtsAccountTrace;
import com.ruoyi.program.mapper.DtsAccountTraceMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Api(tags = {"账户流水"})
@RestController
@RequestMapping("/AccountStatement")
public class DtsAccountTraceController {

    /**
     * 日志记录器，用于记录类的操作日志。
     * 使用LoggerFactory获取特定于类的Logger实例，以便记录DtsAccountTraceController类的操作日志。
     */
    private static final Logger logger = LoggerFactory.getLogger(DtsAccountTraceController.class);

    @Autowired
    private DtsAccountTraceMapper dtsAccountTraceMapper;


    /**
     * 插入DTS账户流水记录
     *
     * @param record DTS账户流水记录的DTO（数据传输对象），包含了需要插入的所有信息。
     * @return 返回一个ResponseEntity对象，其中包含了操作的结果信息。
     * @ApiOperation 注解用于说明该接口的功能。
     * @PostMapping 注解用于指定该方法处理POST请求的URL。
     */
    @ApiOperation(value = "插入账户流水")
    @PostMapping("/insertDtsAccountTrace")
    public ResponseEntity<String> insertDtsAccountTrace(@RequestBody DtsAccountTraceDto record) {
        try {
            // 将DTO转换为实体对象
            // 将 DTO 转换为 Mapper 类所需的实体
            DtsAccountTrace entity = convertDtoToEntity(record);

            // 尝试插入实体对象到数据库
            boolean isInserted = dtsAccountTraceMapper.insertDtsAccountTrace(entity);

            // 根据插入结果返回相应的响应
            if (isInserted) {
                logger.info("账户流水记录插入成功");
                return ResponseEntity.ok("账户流水插入成功");
            } else {
                logger.warn("账户流水记录插入失败");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("账户流水插入失败");
            }
        } catch (DataAccessException e) {
            // 处理数据库访问异常
            logger.error("数据库访问异常，插入账户流水记录时发生异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库访问异常，插入账户流水记录时发生异常");
        } catch (Exception e) {
            // 处理其他异常
            logger.error("插入账户流水记录时发生异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("插入账户流水记录时发生异常");
        }
    }

    /**
     * 将DtsAccountTraceDto对象转换为DtsAccountTrace实体对象
     *
     * @param dto DTS账户流水记录的DTO对象。
     * @return 转换后的DtsAccountTrace实体对象。
     */
    private DtsAccountTrace convertDtoToEntity(DtsAccountTraceDto dto) {
        DtsAccountTrace entity = new DtsAccountTrace();
        // 设置实体对象的属性值，这些值来自DTO对象
        entity.setTraceSn(dto.getTraceSn());
        entity.setUserId(dto.getUserId());
        entity.setType(dto.getType());
        entity.setAmount(dto.getAmount());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setAddTime(new Date()); // 设置添加时间，默认为当前时间
        entity.setStatus(dto.getStatus());
        return entity;
    }

    /**
     * 通过POST请求更新DtsAccountTrace表中的账户流水信息。
     *
     * @param record 包含待更新账户流水信息的请求体。
     * @return 返回一个ResponseEntity对象，包含更新结果的信息。
     * @ApiOperation 注解用于说明该接口的作用是更新账户流水。
     */
    @ApiOperation(value = "更新账户流水")
    @PostMapping("/updateDtsAccountTrace")
    public ResponseEntity<String> updateDtsAccountTrace(@RequestBody DtsAccountTrace record) {
        try {
            // 检查record对象是否为空，以及是否包含有效的ID，如果ID为空则抛出IllegalArgumentException异常。
            // 校验输入的 DTO 是否包含必要的信息
            Optional.ofNullable(record)
                    .map(DtsAccountTrace::getId)
                    .orElseThrow(() -> new IllegalArgumentException("请求数据无效，账户流水ID不能为空"));

            // 调用dtsAccountTraceMapper的updateByPrimaryKey方法更新账户流水信息。
            // 执行更新操作
            dtsAccountTraceMapper.updateByPrimaryKey(record);
            logger.info("账户流水记录更新成功，ID: {}", record.getId());
            // 返回响应，表示账户流水更新成功。
            return ResponseEntity.ok("账户流水更新成功");
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            // 返回响应，表示请求数据无效，并包含具体的错误信息。
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            logger.error("数据库访问异常，更新账户流水记录时发生异常", e);
            // 返回响应，表示更新账户流水时发生了数据库访问异常。
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库访问异常，更新账户流水记录时发生异常");
        } catch (Exception e) {
            logger.error("更新账户流水记录时发生异常", e);
            // 返回响应，表示更新账户流水时发生了未知异常。
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新账户流水记录时发生异常");
        }
    }

    /**
     * 通过GET请求查询DtsAccountTrace账户流水信息。
     * <p>
     * 该接口使用@ApiOperation注解指明其API操作的描述和价值。使用@GetMapping注解指定该方法处理GET类型的HTTP请求。
     * 请求的URL路径为/selectDtsAccountTrace。方法参数example是一个DtsAccountTrace对象，
     * 用于传递查询条件。返回值是一个包含DtsAccountTrace对象列表的ResponseEntity。
     * </p>
     *
     * @param example 查询条件对象，用于指定查询的条件。
     * @return 包含查询结果的ResponseEntity对象。响应体中包含查询到的DtsAccountTrace对象列表。
     */
    @ApiOperation(value = "查询账户流水")
    @GetMapping("/selectDtsAccountTrace")
    public ResponseEntity<List<DtsAccountTrace>> selectDtsAccountTrace(DtsAccountTrace example) {
        // 调用dtsAccountTraceMapper的countByExample方法查询符合条件的账户流水记录总数。
        // 调用dtsAccountTraceMapper的selectByExample方法查询账户流水信息。
        List<DtsAccountTrace> list = dtsAccountTraceMapper.countByExample(example);
        logger.info("查询账户流水记录成功");
        // 返回查询结果，HTTP状态码为200。
        // 返回响应，包含查询到的账户流水列表。
        return ResponseEntity.ok(list);
    }
}
