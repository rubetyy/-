package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Live {
    @Id
    @Column(name="live_pk")
    Long livepk;
    @Column(name="product_pk")
    Long productpk;
    @Column(name="live_title")
    String livetitle;
    @Column(name="live_viewer_count")
    Long liveviewercount;
    @Column(name="user_id")
    String userid;
    @Column(name="is_live")
    Long islive;

}
