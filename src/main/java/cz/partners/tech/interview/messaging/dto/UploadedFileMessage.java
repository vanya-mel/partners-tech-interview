package cz.partners.tech.interview.messaging.dto;

public record UploadedFileMessage(
        String uploadedFileEntityId
) implements RabbitMessage { }
