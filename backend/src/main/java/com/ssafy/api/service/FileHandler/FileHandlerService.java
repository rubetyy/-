package com.ssafy.api.service.FileHandler;

import com.ssafy.api.request.ImageRegister;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileHandlerService {
    public void upload(MultipartFile file);
    public void download(String filename);
    public void deleteFile();
}
