package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

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
    int livepk;
    @Column(name="product_pk")
    int productpk;
    @Column(name="live_title")
    String livetitle;
    @Column(name="live_viewer_count")
    int liveviewercount;
    @Column(name="user_id")
    String userid;

}
