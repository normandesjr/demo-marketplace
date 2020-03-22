package com.hibicode.marketplacekafkastreams.streams.example;

import com.hibicode.marketplacekafkastreams.streams.model.Cart;
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
public class FirstCartExampleStream {

    @Bean
    public NewTopic firstCartExampleInput() {
        return TopicBuilder.name("first-cart-example-input")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic firstCartExampleOutput() {
        return TopicBuilder.name("first-cart-example-output")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KStream<?, Cart> firstCartStream(StreamsBuilder streamsBuilder) {
        KStream<?, Cart> cartStream = streamsBuilder.stream(firstCartExampleInput().name()
                , Consumed.with(Serdes.String(), new JsonSerde<Cart>(Cart.class)));

        cartStream
                .mapValues(cart -> cart.getItem().getSku())
                .to(firstCartExampleOutput().name());

        return cartStream;
    }

}
