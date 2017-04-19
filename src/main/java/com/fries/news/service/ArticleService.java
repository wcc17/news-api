package com.fries.news.service;

import com.fries.news.domain.Article;

import java.util.List;

public interface ArticleService {

    public List<Article> getTopArticles(Integer numberToRetrieve);
    public Long getArticleCount();
    public List<Article> getArticlePage(Integer pageNumber, Integer pageSize);
    public void saveArticle(Article article);
    public void deleteArticle(Integer articleId);
    public Article getArticleById(Integer articleId);
    public Article getArticleByName(String articleName);
}
