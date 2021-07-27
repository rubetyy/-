package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    String userId;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="user_password")
    String password;

    @Column(name="user_nickname")
    String userNickname;

    @Column(name="user_created_at")
    LocalDateTime userCreateAt;

    @Builder
    public User(String userId, String password, String userNickname){
        this.userId = userId;
        this.password = password;
        this.userNickname = userNickname;
    }
}
