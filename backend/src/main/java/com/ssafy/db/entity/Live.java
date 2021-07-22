package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class Live extends BaseEntity{
    Long id;
    @Column(name="userid") String userid;
    String livetitle;
    String liveds;
    String liveimg;
    String livewriter;
    int liveon;
    Date live_reg_date;
    Date live_fin_date;
}
