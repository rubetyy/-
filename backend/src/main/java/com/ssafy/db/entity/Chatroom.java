package com.ssafy.db.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
