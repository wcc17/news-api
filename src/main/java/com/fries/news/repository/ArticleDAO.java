package com.fries.news.repository;

import com.fries.news.domain.Article;

import java.util.List;

public interface ArticleDAO {

    public List<Article> getTopArticles(Integer numberToRetrieve);

    public Long getArticleCount();

    public List<Article> getArticlePage(Integer pageNumber, Integer pageSize);

    public Integer saveArticle(Article article);

    public Integer updateArticle(Article article);

    public void deleteArticle(Article article);

    public Article getArticleById(Integer articleId);

    public Article getArticleByName(String articleName);
}
