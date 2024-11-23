package com.scaler.blog.blogapp.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {

    @Autowired
    ArticlesRepository articlesRepository;

    public List<ArticleEntity> getArticles() {
        List<ArticleEntity> articles = articlesRepository.findAll();
        return articles;
    }

    public ArticleEntity createArticle(ArticleEntity article) {
        ArticleEntity savedArticle = articlesRepository.save(article);
        return savedArticle;
    }

    public ArticleEntity getArticleBySlug(String slug) {
        ArticleEntity article = articlesRepository.findBySlug(slug);
        return article;
    }

    public ArticleEntity updateArticleBySlug(String slug, ArticleEntity article) {
        ArticleEntity dbArticle = getArticleBySlug(slug);
        if(article.getBody() != null) {
            dbArticle.setBody(article.getBody());
        }
        if(article.getTitle() != null) {
            dbArticle.setTitle(article.getTitle());
        }
        if(article.getSubTitle() != null) {
            dbArticle.setSubTitle(article.getSubTitle());
        }
        if(article.getTags() != null) {
            dbArticle.setTags(article.getTags());
        }
        ArticleEntity updatedArticle = articlesRepository.save(dbArticle);
        return updatedArticle;
    }
}
