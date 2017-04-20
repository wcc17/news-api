package com.fries.news.controller;

import com.fries.news.domain.Article;
import com.fries.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    public Integer createArticle(@RequestParam("name") String name,
//                                 @RequestParam("title") String title,
//                                 @RequestParam("sub") String subTitle,
//                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate,
//                                 @RequestParam("content") String content) {
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        try {
//            Article article = new Article();
//            article.setName(name);
//            article.setTitle(title);
//            article.setSubTitle(subTitle);
//            article.setPublishDate(publishDate);
//            article.setContent(content);

            articleService.saveArticle(article);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping(method = RequestMethod.PUT, value = BASE_PATH + "/update")
    public Integer updateArticle(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("title") String title,
                                 @RequestParam("sub") String subTitle,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate,
                                 @RequestParam("content") String content) {
        //http://localhost:8080/article/update?id=23&name=new_article&title=new_title&sub=new_sub&date=2017-03-18&content=thisistestcontent
        try {
            Article article = articleService.getArticleById(id);
            article.setName(name);
            article.setTitle(title);
            article.setSubTitle(subTitle);
            article.setPublishDate(publishDate);
            article.setContent(content);

            articleService.saveArticle(article);
            return article.getId();
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            return -1;
        }
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping(method = RequestMethod.DELETE, value = BASE_PATH + "/delete")
    public Integer deleteArticle(@RequestParam("id") Integer articleId) {
        //http://localhost:8080/article/delete?id=23
        try {
            articleService.deleteArticle(articleId);
        } catch (RuntimeException e) {
            //TODO: need to log the exception without System.out.println(). Can't remember why Steven said it slows things down
            return - 1;
        }

        return 0;
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="id")
    public Article getArticleById(@RequestParam("id") Integer articleId) {
        //http://localhost:8080/article?id=16
        return articleService.getArticleById(articleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH, params="name")
    public Article getArticleByName(@RequestParam("name") String articleName) {
        //http://localhost:8080/article?name=***REMOVED***
        return articleService.getArticleByName(articleName);
    }
}
