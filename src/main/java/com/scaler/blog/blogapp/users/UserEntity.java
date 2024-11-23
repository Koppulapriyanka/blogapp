package com.scaler.blog.blogapp.users;

import com.scaler.blog.blogapp.articles.ArticleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "BLOG_USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="User_Name")
    private String userName;

    @Column(name="Password")
    private String password;

    @Column(name="Email")
    private String email;

    @Column(name="Bio")
    private String bio;

    @Column(name="Image")
    private String image;

    @Column(name = "createdAt")
    @CreatedDate
    private Date createdAt;

    @OneToMany(mappedBy = "author")
    private List<ArticleEntity> articles;
}
