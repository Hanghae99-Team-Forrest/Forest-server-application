package com.forrest.server.web.entity.category;

import com.forrest.server.web.entity.BaseTimeEntity;
import com.forrest.server.web.entity.post.Post;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * description
 * - 카테고리는 DB 로만 생성
 *
 * @Created by Bloo
 * @Date: 2021/07/10
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne ( fetch = FetchType.LAZY,
                 cascade = CascadeType.ALL )

    private Post post;
}
