package com.ssafy.api.service.FileHandler;

import com.ssafy.db.entity.Image;
import com.ssafy.db.repository.Image.ImageRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("fileHandlerService")
public class FileHandlerImpl implements FileHandlerService{

    @Autowired
    ImageRepository fileRepository;

    @Override
    @Transactional
    public List<Image> upload(List<MultipartFile> files) {
        //생성할 이미지 리스트
        List<Image> images = new ArrayList<>();
        if(files.isEmpty()){
            throw new RuntimeException("빈 파일은 저장할 수 없습니다.");
        }
        try{
            //파일명을 업로드한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            //프로젝트 디렉토리 내의 저장을 위한 절대 경로
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            //파일 상세 경로
            String path = "images" + File.separator + current_date;
            File file =  new File(path);

            if(!file.exists()){
                //상위 디렉토리가 존재하지않을때까지 생성
                file.mkdirs();
            }
            for(MultipartFile multipartFile : files){
                //파일 확장자 추출
                String originFileExtension;
                String contentType = multipartFile.getContentType();
                //파일 확장자가 존재하지 않으면 처리하지 않는다.
                if(ObjectUtils.isEmpty(contentType)){
                    break;
                }else{
                    if(contentType.contains("image/jpeg")){
                        originFileExtension = ".jpg";
                    }else if(contentType.contains("image/png")){
                        originFileExtension = ".png";
                    }else if(contentType.contains("image/gif")){
                        originFileExtension = ".gif";
                    }else{
                        break;
                    }
                }
                //파일명 중복 피하기 위해 나노초까지 붙인다
                String newFileName = System.nanoTime() + originFileExtension;
                //파일 생성 후 리스트에 추가
                Image image = Image.builder()
                        .originFileName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + newFileName)
                        .fileSize(multipartFile.getSize())
                        .build();

                images.add(image);

                file = new File(absolutePath + path + File.separator + newFileName);
                multipartFile.transferTo(file);

                //파일 권한 설정
                file.setWritable(true);
                file.setReadable(true);

            }
        }catch (IOException e){
            throw new RuntimeException("파일 저장에 실패했습니다.",e);
        }
        return images;
    }

    @Override
    public List<Image> download(int productId) {
        List<Image> images = fileRepository.findAllByProductId(productId);
        return images;
    }

    @Override
    public void deleteFile() {

    }
}
