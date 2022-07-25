package com.kafka.eventsdemoproducer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(
        prefix = "kafka"
)
@Data
@NoArgsConstructor
public class KafkaProperties {
    List<String> bootstrapServers;
    String acksConfig;
    String retriesConfig;
    Class<?> keySerializer = StringSerializer.class;
    Class<?> valueSerializer = KafkaAvroSerializer.class;
    String schemaRegistryUrl;
}
