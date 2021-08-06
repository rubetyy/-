package com.ssafy.api.service.FileHandler;

import com.ssafy.db.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileHandlerService {
    public List<Image> upload(List<MultipartFile> file);
    public List<Image> download(Long productId);
    public void deleteFile();
}
