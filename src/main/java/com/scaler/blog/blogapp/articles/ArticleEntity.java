package com.scaler.blog.blogapp.articles;

import com.scaler.blog.blogapp.comments.CommentEntity;
import com.scaler.blog.blogapp.users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "ARTICLES")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airticleId;

    @Column(name="Title")
    private String title;

    @Column(name="Slug")
    private String slug;

    @Column(name="SubTitle")
    private String subTitle;

    @Column(name="Body")
    private String body;

    @Column(name="CreateAt")
    @CreatedDate
    private Date createAt;

    @Column(name="Tags")
    private String tags;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity author;

    @OneToMany(mappedBy = "article")
    private List<CommentEntity> comments;

    public List<CommentEntity> getComments() {
        if(comments == null) {
            return new ArrayList<>();
        }
        return comments;
    }
}
