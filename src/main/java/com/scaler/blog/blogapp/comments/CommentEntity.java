package com.scaler.blog.blogapp.comments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scaler.blog.blogapp.articles.ArticleEntity;
import com.scaler.blog.blogapp.users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "COMMENTS")
@EnableJpaAuditing
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Body")
    private String body;

    @Column(name="createdAt")
    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "airticleId")
    @JsonIgnore
    private ArticleEntity article;
    
}
