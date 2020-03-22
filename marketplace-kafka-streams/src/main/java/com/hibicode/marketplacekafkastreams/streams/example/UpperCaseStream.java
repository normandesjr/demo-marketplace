package com.hibicode.marketplacekafkastreams.streams.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UpperCaseStream {

    @Bean
    public NewTopic word() {
        return TopicBuilder.name("word")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic wordUpperCased() {
        return TopicBuilder.name("word-upper-cased")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KStream<String, String> upperCase(StreamsBuilder streamsBuilder) {
        KStream<String, String> upperCaseStream = streamsBuilder.stream(word().name());
        upperCaseStream
                .mapValues(value -> value.toUpperCase())
                .to(wordUpperCased().name());

        return upperCaseStream;
    }


}
