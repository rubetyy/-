package com.ssafy.api.response.dto.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LivewithUser {
    Long livepk;
    Long productpk;
    String livetitle;
    Long liveviewercount;
    String userid;
    String usernickname;
    LocalDateTime usercreatedat;
}
