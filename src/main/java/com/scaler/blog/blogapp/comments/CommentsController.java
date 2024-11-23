package com.scaler.blog.blogapp.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/articles/{article-slug}/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentEntity>> getArticleComments(@PathVariable("article-slug") String articleSlug) {
        List<CommentEntity> comments = commentsService.getCommentsByArticleSlug(articleSlug);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentEntity> addComment(@PathVariable("article-slug") String articleSlug,
                                                    @RequestBody CommentEntity comment){
        CommentEntity savedComment = commentsService.addCommentsToArticle(articleSlug, comment);
        return ResponseEntity.created(URI.create("/articles/{article-slug}/comments/"+ savedComment.getCommentId()))
                .body(savedComment);
    }
}
