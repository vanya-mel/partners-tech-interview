package cz.partners.tech.interview.service;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.payload.response.FileCheckResultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    /**
     * Process the upload file request. Upload the file to the database and creates the message for async scan.
     *
     * @param file file to upload for security check
     * @return id of the saved {@link UploadedFile}
     */
    String uploadFile(MultipartFile file);

    FileCheckResultResponse checkFileProcessResult(String uploadedFileId);

    void processUploadedFile(String uploadedFileId) throws IOException;
}
