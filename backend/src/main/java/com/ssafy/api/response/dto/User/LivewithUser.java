package com.ssafy.api.response.dto.User;

import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LivewithUser {
    Integer livepk;
    Integer productpk;
    String livetitle;
    Integer liveviewercount;
    Integer islive;
    String userid;
    String usernickname;
    boolean flag;
    public LivewithUser(Live l, User u){
        this.livepk = l.getLivepk();
        this.productpk = l.getProductpk();
        this.livetitle = l.getLivetitle();
        this.liveviewercount = l.getLiveviewercount();
        this.islive = l.getIslive();
        this.userid = u.getUserid();
        this. usernickname = u.getUsernickname();
    }
}
