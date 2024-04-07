package qt.qr_backend.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class FileHandler {
    public String parseFileInfo(MultipartFile multipartFile) throws IOException {

        //파일이 비었을 경우
        if(multipartFile.isEmpty()) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String fileSeparator = File.separator; // OS 환경에 맞는 파일 구분자를 제공 (윈도우 : 역슬래시, 리눅스 : 슬래시)

        String absolutePath =  new File("").getAbsolutePath() + fileSeparator;
        String path = "images" + fileSeparator + current_date;

        //해당 경로에 폴더 및 파일이 존재하지 않을 경우 생성
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        if(!multipartFile.isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리할 예정
            String contentType = multipartFile.getContentType();
            String originalFileExtension = "";

            // 확장자 명이 없으면 이 파일은 잘못된 것이다
            if(ObjectUtils.isEmpty(contentType)) {
                return null;
            } else {
                if(contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalFileExtension = ".gif";
                }
            }

            String new_file_name = UUID.randomUUID() + multipartFile.getName() + originalFileExtension;

            file = new File(absolutePath + path + fileSeparator + new_file_name);
            multipartFile.transferTo(file);

            return file.getPath();
        }

        return null;

    }
}
