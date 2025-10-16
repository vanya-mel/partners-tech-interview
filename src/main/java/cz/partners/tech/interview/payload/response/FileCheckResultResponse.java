package cz.partners.tech.interview.payload.response;

import lombok.Builder;

@Builder
public record FileCheckResultResponse (
        String uploadedFileId,
        FileProcessState fileProcessState
) {
}
