package com.ruoyi.program.service.impl;

import com.ruoyi.program.entity.DtsArticle;
import com.ruoyi.program.mapper.DtsArticleMapper;
import com.ruoyi.program.service.DtsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DtsArticleServiceimpl implements DtsArticleService {

    @Autowired
    private DtsArticleMapper dtsArticleMapper;

    /**
     * 插入文章信息。
     * <p>
     * 本方法通过调用dtsArticleMapper的insertArticle方法，将文章对象dtsArticle插入到数据库中。
     * 主要用于文章的新增操作，通过传递文章对象来完成数据的插入。
     *
     * @param dtsArticle 待插入的文章对象，包含文章的全部信息。
     * @return 返回插入操作的影响行数，通常情况下，如果插入成功，返回1；如果插入失败，返回0。
     */
    @Override
    public int insertArticle(DtsArticle dtsArticle) {
        // 调用dtsArticleMapper的insertArticle方法插入文章
        int insertArticle = dtsArticleMapper.insertArticle(dtsArticle);
        // 返回插入操作的影响行数
        return insertArticle;
    }

    /**
     * 更新文章信息的方法。
     * 此方法覆盖了父类或接口中的同名方法。
     *
     * @param dtsArticle 文章对象，包含要更新的文章数据。
     *                   包含字段如ID、标题、内容、作者等。
     * @return 返回一个整数，表示受影响的行数。
     * 如果返回值大于0，表示至少有一行数据被成功更新；
     * 如果返回值为0，表示没有数据被更新；
     * 如果出现错误，可能会抛出异常而非返回负数。
     */
    @Override
    public int updateArticle(DtsArticle dtsArticle) {
        int updateArticle = dtsArticleMapper.updateArticle(dtsArticle);
        return updateArticle;
    }

    /**
     * 查询文章列表
     * <p>
     * 本方法通过调用dtsArticleMapper的selectArticleList方法，获取文章列表。
     * 它不接受任何参数，返回一个DtsArticle类型的列表。
     * 这个方法是对上级接口方法的实现，通过这种方式，实现了对数据库中文章数据的查询操作。
     *
     * @return List<DtsArticle> 返回一个包含文章信息的列表
     */
    @Override
    public List<DtsArticle> selectArticleList() {
        // 调用dtsArticleMapper的selectArticleList方法查询文章列表
        List<DtsArticle> dtsArticles = dtsArticleMapper.selectArticleList();
        // 返回查询结果
        return dtsArticles;
    }

    /**
     * 根据标题模糊查询文章列表。
     *
     * @param title 文章标题，用于模糊匹配
     * @return 匹配到的文章列表，如果无匹配结果则返回空列表
     */
    @Override
    public List<DtsArticle> selectArticleListFuzzyQuery(String title) {
        List<DtsArticle> dtsArticles = dtsArticleMapper.selectArticleListFuzzyQuery(title);
        return dtsArticles;
    }

    /**
     * 删除指定ID的文章。
     *
     * @param id 文章的唯一标识
     * @return 如果删除成功（影响行数大于0），返回true，否则返回false
     */
    @Override
    public boolean deleteArticle(Integer id) {
        return dtsArticleMapper.deleteArticle(id) > 0;
    }
}
