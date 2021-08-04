package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @Column(name="user_id")
    String userid;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="user_password")
    String userpassword;

    @Column(name="user_nickname")
    String usernickname;

    @Column(name="user_created_at")
    LocalDateTime userCreateAt;

    @Builder
    public User(String id, String password, String nickname){
        this.userid = id;
        this.userpassword = password;
        this.usernickname = nickname;
    }
}
