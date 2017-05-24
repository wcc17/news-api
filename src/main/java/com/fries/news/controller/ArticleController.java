package com.fries.news.controller;

import com.fries.news.annotation.AdminRestricted;
import com.fries.news.domain.Article;
import com.fries.news.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ArticleController {

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    public static final String BASE_PATH = "/article";

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH)
    public List<Article> pageOfArticles(@RequestParam("page") Integer pageNumber,
                                        @RequestParam("size") Integer pageSize) {
        try {
            //http://localhost:8080/article?page=0&size=10
            return articleService.getArticlePage(pageNumber, pageSize);
        } catch (Exception e) {
            LOGGER.error("Error retrieving a page of articles with page {} and size {}", pageNumber, pageSize, e);
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/count")
    public Long articleCount() {
        try {
            //http://localhost:8080/article/count
            return articleService.getArticleCount();
        } catch (Exception e) {
            LOGGER.error("Error retrieving article count", e);
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/top")
    public List<Article> topArticles(@RequestParam("number") Integer numberToRetrieve) {
        try {
            //http://localhost:8080/article/top?number=3
            return articleService.getTopArticles(numberToRetrieve);
        } catch (Exception e) {
            LOGGER.error("Error retrieving top articles with numberToRetrieve: {}", numberToRetrieve, e);
            return null;
        }
    }

    @AdminRestricted
    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH + "/create")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        try {
            Integer articleId = articleService.saveArticle(article);
            URI location = ServletUriComponentsBuilder
                    .fromPath("{id}")
                    .buildAndExpand(articleId).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            LOGGER.error("Error creating article with title {}", article.getTitle(), e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @AdminRestricted
    @RequestMapping(method = RequestMethod.PUT, value = BASE_PATH + "/update")
    public ResponseEntity<?> updateArticle(@RequestBody Article article) {
        try {
            articleService.updateArticle(article);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            LOGGER.error("Error updating article with id {}", article.getId(), e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @AdminRestricted
    @RequestMapping(method = RequestMethod.DELETE, value = BASE_PATH + "/delete")
    public ResponseEntity<?> deleteArticle(@RequestParam("id") Integer articleId) {
        //http://localhost:8080/article/delete?id=23
        try {
            articleService.deleteArticle(articleId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            LOGGER.error("Error deleting article with id {}", articleId, e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="id")
    public Article getArticleById(@RequestParam("id") Integer articleId) {
        try {
            //http://localhost:8080/article?id=16
            return articleService.getArticleById(articleId);
        } catch (Exception e) {
            LOGGER.error("Error retrieving article with id {}", articleId, e);
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="name")
    public Article getArticleByName(@RequestParam("name") String articleName) {
        try {
            //http://localhost:8080/article?name=testname
            return articleService.getArticleByName(articleName);
        } catch (Exception e) {
            LOGGER.error("Error getting article by name {}", articleName, e);
            return null;
        }
    }
}
