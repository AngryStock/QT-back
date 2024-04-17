package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qt.qr_backend.controller.response.CeoImagesUrlResponse;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.repository.CeoRepository;
import qt.qr_backend.service.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final CeoRepository ceoRepository;
    private final FileHandler fileHandler;

    @GetMapping("/{date}/{filename}")
    @ResponseBody
    public ResponseEntity<Object> getImage(@PathVariable String date, @PathVariable String name) {
        String url = "images/"+date+"/"+name;
        return makeImageResponse(url);
    }

    //공사중
    //사업자 등록증, 영업 신고증, 통장 사본 이미지 파일 form으로 옴
    @PostMapping("/ceo")
    public ResponseEntity<Object> saveCeoImages(
            @RequestParam MultipartFile businessReportCertificateFile,
            @RequestParam MultipartFile businessRegistrationFile,
            @RequestParam MultipartFile copyOfBankbookFile) throws IOException {

        String businessReportCertificateFileUrl = fileHandler.parseFileInfo(businessReportCertificateFile);
        String businessRegistrationFileUrl = fileHandler.parseFileInfo(businessRegistrationFile);
        String copyOfBankbookFileUrl = fileHandler.parseFileInfo(copyOfBankbookFile);

        // Url 값이 null일 경우 파일을 첨부하지 않고 보낸 것이므로 에러를 응답해야함
        if(businessReportCertificateFileUrl == null || businessRegistrationFileUrl == null
                || copyOfBankbookFileUrl == null) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "파일을 첨부하지 않았습니다");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        log.info("businessReportCertificateFileUrl = {}", businessReportCertificateFileUrl);
        log.info("businessRegistrationFileUrl = {}", businessRegistrationFileUrl);
        log.info("copyOfBankbookFileUrl = {}", copyOfBankbookFileUrl);

        CeoImagesUrlResponse ceoImagesUrlResponse = new CeoImagesUrlResponse(businessReportCertificateFileUrl, businessRegistrationFileUrl, copyOfBankbookFileUrl);

        return new ResponseEntity<>(ceoImagesUrlResponse, HttpStatus.OK);
    }

//    @GetMapping("/businessReportCertificateFile/{id}")
//    @ResponseBody
//    public ResponseEntity<Object> getBusinessReportCertificateFile(@PathVariable String id) {
//        Ceo ceo = ceoRepository.findById(id);
//        String url = ceo.getBusinessReportCertificateFileUrl();
//
//        System.out.println("businessReportCertificateFileUrl = " + url);
//
//        return makeImageResponse(url);
//    }
//
//    @GetMapping("/businessRegistrationFile/{id}")
//    public ResponseEntity<Object> getBusinessRegistrationFile(@PathVariable String id) {
//        Ceo ceo = ceoRepository.findById(id);
//        String url = ceo.getBusinessRegistrationFileUrl();
//
//        System.out.println("businessRegistrationFileUrl = " + url);
//
//        return makeImageResponse(url);
//    }
//
//    @GetMapping("/copyOfBankbookFile/{id}")
//    public ResponseEntity<Object> getCopyOfBankbookFile(@PathVariable String id) {
//        Ceo ceo = ceoRepository.findById(id);
//        String url = ceo.getCopyOfBankbookFileUrl();
//
//        System.out.println("copyOfBankbookFileUrl = " + url);
//
//        return makeImageResponse(url);
//    }

    // 이미지 응답 만들어서 반환
    private ResponseEntity<Object> makeImageResponse(String url) {
        Resource resource = new FileSystemResource(url);

        if(!resource.exists()) {
            System.out.println("IMAGE FILE NOT FOUND");
            ErrorResponse errorResponse = new ErrorResponse(404, "이미지 파일을 찾을 수 없습니다.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath =  Paths.get(url);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resource, header, HttpStatus.OK);

    }

}
