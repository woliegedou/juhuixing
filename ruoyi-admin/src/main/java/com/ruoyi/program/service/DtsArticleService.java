package com.ruoyi.program.service;

import com.ruoyi.program.entity.DtsArticle;

import java.util.List;

public interface DtsArticleService {

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
     *
     * @param id
     * @return
     */
    boolean deleteArticle(Integer id);
}
