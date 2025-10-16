package cz.partners.tech.interview.messaging.producer;

import cz.partners.tech.interview.messaging.dto.RabbitMessage;

public interface RabbitProducer<T extends RabbitMessage> {

    void sendMessage(T message);
}
