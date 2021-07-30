package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ImageRegister")
public class ImageRegister {
    String fileName;
    String filePath;
    String originFileName;
}
