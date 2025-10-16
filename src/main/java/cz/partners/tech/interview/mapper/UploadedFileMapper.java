package cz.partners.tech.interview.mapper;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.domain.enums.ProcessState;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class UploadedFileMapper {

    public UploadedFile maptToUploadedFile(final MultipartFile file) {
        final UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFile.setContentType(file.getContentType());
        uploadedFile.setSize(file.getSize());

        return uploadedFile;
    }

    public void decorateUploadedFileWithProcessState(final UploadedFile uploadedFile, final ProcessState processState) {
        uploadedFile.setProcessState(processState);
    }

    public void decorateUploadedFileWithFileId(final UploadedFile uploadedFile, final String savedFileId) {
        uploadedFile.setFileId(savedFileId);
    }

    public ProcessState mapToProcessState(final boolean scanResult) {
        return scanResult ? ProcessState.FINISHED_OK : ProcessState.FINISHED_NOK;
    }
}
