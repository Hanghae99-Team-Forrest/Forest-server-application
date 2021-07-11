package com.forrest.server.web.entity.post;

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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false )
    private int grade;

    @Column(nullable = false)
    private boolean open;

    @ManyToOne ( fetch = FetchType.LAZY,
                 cascade = CascadeType.ALL )
    private Category category;

    @Builder
    public Post ( String title, String content, int grade, boolean open ) {
        this.title = title;
        this.content = content;
        this.grade = grade;
        this.open = open;
    }

}
