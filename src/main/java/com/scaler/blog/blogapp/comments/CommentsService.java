package com.scaler.blog.blogapp.comments;

import com.scaler.blog.blogapp.articles.ArticleEntity;
import com.scaler.blog.blogapp.articles.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    ArticlesRepository articlesRepository;

    public List<CommentEntity> getCommentsByArticleSlug(String slug) {
        return commentsRepository.findAllByArticleSlug(slug);
    }

    public CommentEntity addCommentsToArticle(String slug, CommentEntity comment) {
        ArticleEntity article =  articlesRepository.findBySlug(slug);
        comment.setArticle(article);
        CommentEntity savedComment = commentsRepository.save(comment);
        return savedComment;
    }

}
