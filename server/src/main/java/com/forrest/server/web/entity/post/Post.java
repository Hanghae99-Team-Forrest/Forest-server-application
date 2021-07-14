package com.forrest.server.web.entity.post;

import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.entity.BaseTimeEntity;
import com.forrest.server.web.entity.category.Category;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String imgUrl;


//    @Column(nullable = false )
//    private int grade;


//    @Column(nullable = false)
//    private boolean open;

    @ManyToOne ( fetch = FetchType.LAZY,
                 cascade = CascadeType.ALL )
    private Category category;

    @Builder
    public Post ( String userName, String title, String content, String password,
        String imgUrl, Category category ) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.password = password;
        this.imgUrl = imgUrl;
        this.category = category;
    }
    //    @Builder
//    public Post ( String userName, String title, String content, int grade, String imgUrl,
//        boolean open, Category category ) {
//        this.userName = userName;
//        this.title = title;
//        this.content = content;
//        this.grade = grade;
//        this.imgUrl = imgUrl;
//        this.open = open;
//        this.category = category;
//    }
}
