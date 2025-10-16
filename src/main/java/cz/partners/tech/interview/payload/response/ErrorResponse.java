package cz.partners.tech.interview.payload.response;

import lombok.Builder;

import java.util.Map;

@Builder
public record ErrorResponse (
        String status,
        int statusCode,
        String description,
        Map<String, String> fieldsErrors
) {
}
