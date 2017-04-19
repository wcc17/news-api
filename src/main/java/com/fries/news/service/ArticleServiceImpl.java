package com.fries.news.service;

import com.fries.news.domain.Article;
import com.fries.news.repository.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDAO articleDAO;

    @Override
    public List<Article> getTopArticles(Integer numberToRetrieve) {
        return articleDAO.getTopArticles(numberToRetrieve);
    }

    @Override
    public Long getArticleCount() {
        return articleDAO.getArticleCount();
    }

    @Override
    public List<Article> getArticlePage(Integer pageNumber, Integer pageSize) {
        return articleDAO.getArticlePage(pageNumber, pageSize);
    }

    @Override
    public void saveArticle(Article article) {
        articleDAO.saveArticle(article);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        Article article = getArticleById(articleId);
        articleDAO.deleteArticle(article);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleDAO.getArticleById(articleId);
    }

    @Override
    public Article getArticleByName(String articleName) {
        return articleDAO.getArticleByName(articleName);
    }
}
