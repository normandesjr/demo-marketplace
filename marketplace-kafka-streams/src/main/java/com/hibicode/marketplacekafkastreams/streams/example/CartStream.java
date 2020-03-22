package com.hibicode.marketplacekafkastreams.streams.example;

import com.hibicode.marketplacekafkastreams.streams.model.Cart;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class CartStream {

    @Bean
    public NewTopic cartInput() {
        return TopicBuilder.name("cart-input")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic customerSku() {
        return TopicBuilder.name("customer-sku")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic cartOutput() {
        return TopicBuilder.name("cart-output")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KStream<?, Cart> cartStreamCreator(StreamsBuilder streamsBuilder) {
        KStream<?, Cart> cartStream = streamsBuilder.stream(cartInput().name()
                , Consumed.with(Serdes.String(), new JsonSerde<Cart>(Cart.class)));

        cartStream
                .filter((key, cart) -> cart != null && cart.getItem() != null)
                .selectKey((customerId, cart) -> String.format("%s-%s", customerId, cart.getItem().getSku()))
                .mapValues(cart -> cart.getItem().getQuantity())
                .to(customerSku().name(), Produced.with(Serdes.String(), Serdes.Integer()));

        KStream<String, Integer> customerSkuStream = streamsBuilder.stream(customerSku().name()
                , Consumed.with(Serdes.String(), Serdes.Integer()));

        customerSkuStream
                .groupByKey()
                .aggregate(() -> 0, (newKey, newValue, aggValue) -> newValue + aggValue
                        , Materialized.with(Serdes.String(), Serdes.Integer()))
                .toStream()
                .to(cartOutput().name());

        return cartStream;
    }

}
