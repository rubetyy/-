package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdatePostRequest")
public class UserUpdatePostReq {
    @ApiModelProperty(name="PK")
    String id;
    @ApiModelProperty(name="유저 ID", example="ssafy_web")
    String user_id;
    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;
    @ApiModelProperty(name="유저 Department")
    String department;
    @ApiModelProperty(name="유저 name")
    String name;
    @ApiModelProperty(name="유저 position")
    String position;
}
