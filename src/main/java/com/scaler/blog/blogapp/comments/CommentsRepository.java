package com.scaler.blog.blogapp.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByArticleSlug(String slug);

}
