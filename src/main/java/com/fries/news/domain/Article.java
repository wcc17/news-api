package com.fries.news.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fries.news.Util.DateDeserializer;
import com.fries.news.converter.LocalDateAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String subTitle;

    @NotNull
    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate publishDate;

    @NotNull
    @Column
    @Lob
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
