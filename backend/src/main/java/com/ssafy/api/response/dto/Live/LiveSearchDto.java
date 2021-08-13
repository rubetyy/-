package com.ssafy.api.response.dto.Live;

import com.ssafy.db.entity.Live;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class LiveSearchDto {
    @Id
    @Column(name="live_pk")
    Integer livepk;
    @Column(name="product_pk")
    Integer productpk;
    @Column(name="live_title")
    String livetitle;
    @Column(name="live_viewer_count")
    Integer liveviewercount;
    @Column(name="user_id")
    String userid;
    String filepath;
    public LiveSearchDto(Live l, String filepath){
        this.livepk = l.getLivepk();
        this.productpk = l.getProductpk();
        this.livetitle = l.getLivetitle();
        this.liveviewercount = l.getLiveviewercount();
        this.userid = l.getUserid();
        this.filepath = filepath;
    }
}
