package com.fries.news.repository;

import com.fries.news.domain.Article;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Article> getAllArticles() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Article.class);
        return criteria.list();
    }

    @Override
    public List<Article> getTopArticles(Integer numberToRetrieve) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Article.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(numberToRetrieve);

        Order descendingOrder = Order.desc("publishDate");
        criteria.addOrder(descendingOrder);

        return criteria.list();
    }

    @Override
    public Long getArticleCount() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<Article> getArticlePage(Integer pageNumber, Integer pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.setFirstResult(pageNumber * pageSize);
        criteria.setMaxResults(pageSize);

        Order descendingOrder = Order.desc("publishDate");
        criteria.addOrder(descendingOrder);

        return criteria.list();
    }

    @Override
    public void saveArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(article);
    }

    @Override
    public void deleteArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(article);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.add(Restrictions.eq("id", articleId));

        return (Article) criteria.uniqueResult();
    }

    @Override
    public Article getArticleByName(String articleName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.add(Restrictions.eq("name", articleName));

        return (Article) criteria.uniqueResult();
    }
}
