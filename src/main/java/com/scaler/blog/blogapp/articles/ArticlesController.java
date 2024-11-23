package com.scaler.blog.blogapp.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleEntity>> getArticles() {
        List<ArticleEntity> articles = articlesService.getArticles();
        return ResponseEntity.ok(articles);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleEntity> createArticle(@RequestBody ArticleEntity articleEntity) {
        ArticleEntity savedArticle = articlesService.createArticle(articleEntity);
        return ResponseEntity.created(URI.create("/articles/"+savedArticle.getAirticleId())).body(savedArticle);
    }

    @GetMapping(value = "/{article-slug}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleEntity> getArticle(@PathVariable("article-slug") String articleSlug) {
        ArticleEntity article = articlesService.getArticleBySlug(articleSlug);
        return ResponseEntity.ok(article);
    }

    @PatchMapping(value = "/{article-slug}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleEntity> updateArticleBySlug(@PathVariable("article-slug") String articleSlug,
                                                             @RequestBody ArticleEntity article) {
        ArticleEntity updatedArticle = articlesService.updateArticleBySlug(articleSlug, article);
        return ResponseEntity.ok(updatedArticle);
    }
}
