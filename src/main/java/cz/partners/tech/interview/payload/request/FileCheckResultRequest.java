package cz.partners.tech.interview.payload.request;

import jakarta.validation.constraints.NotBlank;

public record FileCheckResultRequest(
        @NotBlank
        String uploadedFileId
) {

}
