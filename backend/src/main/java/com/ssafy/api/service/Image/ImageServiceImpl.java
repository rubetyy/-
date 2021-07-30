package com.ssafy.api.service.Image;

import com.ssafy.api.response.dto.Image.ImageResponseDto;
import com.ssafy.api.response.dto.Product.ProductListResponseDto;
import com.ssafy.db.entity.Image;
import com.ssafy.db.repository.Image.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ImageResponseDto> findAllByProduct(Long productId) {
        return imageRepository.findAllByProductId(productId)
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
