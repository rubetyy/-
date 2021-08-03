package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="chat_room")
public class Chatroom {
    @Id
    @Column(name="chat_room_pk")
    Long chatroompk;

    @Column(name="product_pk")
    Long productpk;

    @Column(name="user_id_buyer")
    String useridbuyer;

    @Column(name="chat_room_is_buyer")
    Long buy;

    @Column(name="user_id_seller")
    String useridseller;
}
