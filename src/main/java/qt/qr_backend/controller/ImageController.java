package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.repository.CeoRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final CeoRepository ceoRepository;

    @GetMapping("/image")
    @ResponseBody
    public ResponseEntity<Object> getImage(@RequestParam String url) {
        return makeImageResponse(url);
    }

    @GetMapping("/businessReportCertificateFile/{id}")
    @ResponseBody
    public ResponseEntity<Object> getBusinessReportCertificateFile(@PathVariable String id) {
        Ceo ceo = ceoRepository.findById(id);
        String url = ceo.getBusinessReportCertificateFileUrl();

        System.out.println("businessReportCertificateFileUrl = " + url);

        return makeImageResponse(url);
    }

    @GetMapping("/businessRegistrationFile/{id}")
    public ResponseEntity<Object> getBusinessRegistrationFile(@PathVariable String id) {
        Ceo ceo = ceoRepository.findById(id);
        String url = ceo.getBusinessRegistrationFileUrl();

        System.out.println("businessRegistrationFileUrl = " + url);

        return makeImageResponse(url);
    }

    @GetMapping("/copyOfBankbookFile/{id}")
    public ResponseEntity<Object> getCopyOfBankbookFile(@PathVariable String id) {
        Ceo ceo = ceoRepository.findById(id);
        String url = ceo.getCopyOfBankbookFileUrl();

        System.out.println("copyOfBankbookFileUrl = " + url);

        return makeImageResponse(url);
    }

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
