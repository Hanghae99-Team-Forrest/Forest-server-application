package com.forrest.server.web.entity.user;

import com.forrest.server.web.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kakaoId;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private String nickName;

    // TODO: 2021.07.11 -Blue 프로필 이미지 등록 정책을 결정 후 기능을 개발 합니다.
//    @Column(nullable = false)
//    private String profileImg;

    @Builder
    public User ( @NonNull String email, @NonNull String password, @NonNull String nickName ) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    @Builder(builderMethodName = "kakoBuilder")
    public User ( Long kakaoId, @NonNull String email, @NonNull String password, @NonNull String nickName ) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }


}
