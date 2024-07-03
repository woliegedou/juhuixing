package com.ruoyi.program.mapper;

import com.ruoyi.program.entity.DtsArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtsArticleMapper {

    /**
     * 插入文章信息
     *
     * @param dtsArticle
     * @return
     */
    int insertArticle(DtsArticle dtsArticle);

    /**
     * 修改文章信息
     *
     * @param dtsArticle
     * @return
     */
    int updateArticle(DtsArticle dtsArticle);

    /**
     * 查询文章信息
     *
     * @return
     */
    List<DtsArticle> selectArticleList();

    /**
     * 模糊查询文章信息
     *
     * @param title
     * @return
     */
    List<DtsArticle> selectArticleListFuzzyQuery(String title);

    /**
     * 通过id删除文章信息
     * @param id
     * @return
     */
    int deleteArticle(Integer id);
}
