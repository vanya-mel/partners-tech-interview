package cz.partners.tech.interview.messaging.consumer;

import cz.partners.tech.interview.messaging.dto.UploadedFileMessage;
import cz.partners.tech.interview.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitUploadedFileMessageConsumer {

    private final FileUploadService fileUploadService;

    @RabbitListener(queues = {"${spring.rabbitmq.queueName}"})
    public void consumeUploadedFileMessage(final UploadedFileMessage uploadedFileMessage) throws IOException {
        log.info("Consuming uploaded file message with id {}.", uploadedFileMessage.uploadedFileEntityId());
        fileUploadService.processUploadedFile(uploadedFileMessage.uploadedFileEntityId());
    }
}
