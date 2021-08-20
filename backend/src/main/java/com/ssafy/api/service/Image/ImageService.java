package com.ssafy.api.service.Image;

import com.ssafy.api.response.dto.Image.ImageResponseDto;

import java.util.List;

public interface ImageService {
    public List<ImageResponseDto> findAllByProduct(int productId);
}
