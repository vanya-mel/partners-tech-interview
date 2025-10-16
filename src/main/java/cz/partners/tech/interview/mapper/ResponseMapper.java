package cz.partners.tech.interview.mapper;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.payload.response.FileCheckResultResponse;
import cz.partners.tech.interview.payload.response.FileProcessState;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMapper {

    public FileCheckResultResponse mapToFileCheckResult(final UploadedFile uploadedFile) {
        return FileCheckResultResponse.builder()
                .uploadedFileId(uploadedFile.getId())
                .fileProcessState(FileProcessState.from(uploadedFile.getProcessState()))
                .build();
    }
}
