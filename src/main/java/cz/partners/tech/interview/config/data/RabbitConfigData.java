package cz.partners.tech.interview.config.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitConfigData {

    String queueName;

    String exchangeName;

    String routingKey;

}
