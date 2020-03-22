package com.hibicode.marketplacekafkastreams.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.streams.RecoveringDeserializationExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
@EnableKafka
public class KafkaStreamsConfig {

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${cache.max.bytes.buffering}")
    private String cacheMaxBytesBuffering;

    @Autowired
	private KafkaTemplate kafkaTemplate;

    @Bean
	public KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
		Map<String, Object> config = new HashMap<>();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "marketplace");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		config.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
				RecoveringDeserializationExceptionHandler.class);
		config.put(RecoveringDeserializationExceptionHandler.KSTREAM_DESERIALIZATION_RECOVERER, recoverer());

		// we disable the cache to demonstrate all the "steps" involved in the transformation - not recommended in prod
		config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, cacheMaxBytesBuffering);

		return new KafkaStreamsConfiguration(config);
	}

	@Bean
	public DeadLetterPublishingRecoverer recoverer() {
		return new DeadLetterPublishingRecoverer(kafkaTemplate,
				(record, ex) -> new TopicPartition("recovererDLQ", -1));
	}

}
