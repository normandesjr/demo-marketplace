package com.hibicode.marketplacekafkastreams.streams.example;

import com.hibicode.marketplacekafkastreams.streams.model.Client;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class FirstJsonStream {

    @Bean
    public NewTopic firstJsonInput() {
        return TopicBuilder.name("first-json-input")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic firstJsonOutput() {
        return TopicBuilder.name("first-json-output")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KStream<?, Client> firstJsonInputStream(StreamsBuilder builder) {
        KStream<?, Client> clientStream = builder.stream(firstJsonInput().name()
                , Consumed.with(Serdes.String(), new JsonSerde<Client>(Client.class)));

        clientStream
                .mapValues(client -> client.getName().toUpperCase())
                .to(firstJsonOutput().name());

        return clientStream;
    }

}
