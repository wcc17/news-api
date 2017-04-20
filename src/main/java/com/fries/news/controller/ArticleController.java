package com.fries.news.controller;

import com.fries.news.domain.Article;
import com.fries.news.service.ArticleService;
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

    public static final String BASE_PATH = "/article";

    @Autowired
    ArticleService articleService;


    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH)
    public List<Article> pageOfArticles(@RequestParam("page") Integer pageNumber,
                                        @RequestParam("size") Integer pageSize) {
        //http://localhost:8080/article?page=0&size=10
        return articleService.getArticlePage(pageNumber, pageSize);
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/count")
    public Long articleCount() {
        //http://localhost:8080/article/count
        return articleService.getArticleCount();
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/top")
    public List<Article> topArticles(@RequestParam("number") Integer numberToRetrieve) {
        //http://localhost:8080/article/top?number=3
        return articleService.getTopArticles(numberToRetrieve);
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH + "/create")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        try {
            Integer articleId = articleService.saveArticle(article);
            URI location = ServletUriComponentsBuilder
                    .fromPath("{id}")
                    .buildAndExpand(articleId).toUri();

            return ResponseEntity.created(location).build();
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping(method = RequestMethod.PUT, value = BASE_PATH + "/update")
    public ResponseEntity<?> updateArticle(@RequestBody Article article) {
        try {
            articleService.updateArticle(article);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping(method = RequestMethod.DELETE, value = BASE_PATH + "/delete")
    public ResponseEntity<?> deleteArticle(@RequestParam("id") Integer articleId) {
        //http://localhost:8080/article/delete?id=23
        try {
            articleService.deleteArticle(articleId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="id")
    public Article getArticleById(@RequestParam("id") Integer articleId) {
        //http://localhost:8080/article?id=16
        return articleService.getArticleById(articleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="name")
    public Article getArticleByName(@RequestParam("name") String articleName) {
        //http://localhost:8080/article?name=kellyanne-conway-convinced-her-dog-bugged
        return articleService.getArticleByName(articleName);
    }
}
