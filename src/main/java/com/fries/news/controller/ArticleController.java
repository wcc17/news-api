package com.fries.news.controller;

import com.fries.news.domain.Article;
import com.fries.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("/articles")
    public List<Article> pageOfArticles(@RequestParam("page") Integer pageNumber,
                                        @RequestParam("size") Integer pageSize) {
        return articleService.getArticlePage(pageNumber, pageSize);
    }

    @RequestMapping("/articleCount")
    public Long articleCount() {
        return articleService.getArticleCount();
    }

    @RequestMapping("/topArticles")
    public List<Article> topArticles(@RequestParam("number") Integer numberToRetrieve) {
        return articleService.getTopArticles(numberToRetrieve);
    }

    //TODO: THIS NEEDS TO BE RESTRICTED IN PRODUCTION
    @RequestMapping("/create")
    public Integer createArticle(@RequestParam("name") String name,
                                 @RequestParam("title") String title,
                                 @RequestParam("sub") String subTitle,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate,
                                 @RequestParam("content") String content) {
        //http://localhost:8080/create?name=new_article&title=new_title&sub=new_sub&date=2017-03-18&content=thisistestcontent

        if(name != null
                && !name.isEmpty()
                && title != null
                && !title.isEmpty()
                && subTitle != null
                && !subTitle.isEmpty()
                && publishDate != null
                && content != null
                && !content.isEmpty()) {
            Article article = new Article();
            article.setName(name);
            article.setTitle(title);
            article.setSubTitle(subTitle);
            article.setPublishDate(publishDate);
            article.setContent(content);

            articleService.saveArticle(article);
            return article.getId();
        }

        //return an invalid article Id
        return -1;
    }

    @RequestMapping("/delete")
    public void deleteArticle(@RequestParam("id") Integer articleId) {
        articleService.deleteArticle(articleId);
    }

    @RequestMapping(value="/article", params="id")
    public Article getArticleById(@RequestParam("id") Integer articleId) {
        return articleService.getArticleById(articleId);
    }

    @RequestMapping(value="/article", params="name")
    public Article getArticleByName(@RequestParam("name") String articleName) {
        return articleService.getArticleByName(articleName);
    }

    //    @RequestMapping("/articles")
//    public List<Article> allArticles() {
//        return articleService.getAllArticles();
//    }

}
