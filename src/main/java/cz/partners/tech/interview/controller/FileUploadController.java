package cz.partners.tech.interview.controller;

import cz.partners.tech.interview.payload.request.FileCheckResultRequest;
import cz.partners.tech.interview.payload.response.FileCheckResultResponse;
import cz.partners.tech.interview.payload.response.FileUploadResponse;
import cz.partners.tech.interview.service.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file/v1")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        final String savedUploadedFileId = fileUploadService.uploadFile(file);
        return ResponseEntity.ok(new FileUploadResponse(savedUploadedFileId));
    }

    @PostMapping("/check-result")
    public ResponseEntity<FileCheckResultResponse> checkResult(@RequestBody @Valid FileCheckResultRequest request) {
        return ResponseEntity.ok(fileUploadService.checkFileProcessResult(request.uploadedFileId()));
    }

}
