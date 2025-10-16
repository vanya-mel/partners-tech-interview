package cz.partners.tech.interview.messaging.producer.impl;

import cz.partners.tech.interview.config.data.RabbitConfigData;
import cz.partners.tech.interview.messaging.dto.UploadedFileMessage;
import cz.partners.tech.interview.messaging.producer.RabbitProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadedFileRabbitProducer implements RabbitProducer<UploadedFileMessage> {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfigData rabbitConfigData;

    @Override
    public void sendMessage(final UploadedFileMessage message) {
        log.info("Sending message with uploaded file id [{}] to Rabbit.", message.uploadedFileEntityId());
        rabbitTemplate.convertAndSend(rabbitConfigData.getExchangeName(), rabbitConfigData.getRoutingKey(), message);
    }
}
