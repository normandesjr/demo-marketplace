package com.hibicode.marketplacekafkastreams.streams;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CartToItemStream {

    @Bean
    public KStream<String, String> processCartCreateToItemTopic(StreamsBuilder builder) {

    }

}
