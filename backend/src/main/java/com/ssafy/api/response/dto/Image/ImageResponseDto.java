package com.ssafy.api.response.dto.Image;

import com.ssafy.db.entity.Image;
import lombok.Getter;

@Getter
public class ImageResponseDto {
    private Integer imageId;

    public ImageResponseDto(Image entity){
        this.imageId = entity.getImageId();
    }
}
