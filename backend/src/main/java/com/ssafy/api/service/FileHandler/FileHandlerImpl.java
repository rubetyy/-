package com.ssafy.api.service.FileHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service("fileHandlerService")
public class FileHandlerImpl implements FileHandlerService{
    @Value("{uploads}")
    private String path;

    @Override
    public void upload(MultipartFile file) {
        if(file.isEmpty()){
            throw new RuntimeException("빈 파일은 저장할 수 없습니다.");
        }
        try{
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            String msg = String.format("%f 파일 저장에 실패했습니다.", file.getName());
            throw new RuntimeException(msg,e);
        }

    }

    @Override
    public void download(String filename) {

    }

    @Override
    public void deleteFile() {

    }
}
