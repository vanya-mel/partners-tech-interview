package cz.partners.tech.interview.service;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.domain.enums.ProcessState;
import org.springframework.web.multipart.MultipartFile;

public interface UploadedFileService {

    /**
     * Saves the file entity (collection) for further processing.
     *
     * @param file         file to be saved for security checks
     * @param storedFileId the id of the saved file in the storage
     *
     * @return saved {@link UploadedFile} information
     */
    UploadedFile saveFileInformation(MultipartFile file, String storedFileId);

    UploadedFile getUploadedFile(String uploadedFileId);

    UploadedFile getUploadedFile(String uploadedFileId, ProcessState processState);

    void updateUploadedFileProcessState(String uploadedFileId, ProcessState processState);
}
