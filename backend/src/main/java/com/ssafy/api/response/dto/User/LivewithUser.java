package com.ssafy.api.response.dto.User;

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
    LocalDateTime usercreatedat;
}
