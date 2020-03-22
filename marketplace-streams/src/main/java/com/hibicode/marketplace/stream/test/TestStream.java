package com.hibicode.marketplace.stream.test;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(TestBinding.class)
public class TestStream {

    @StreamListener
    @SendTo("output2")
    public KStream<String, Long> handleTest(@Input("input") KTable<String, String> input) {
        return input
                .groupBy((user, colour) -> new KeyValue<>(colour, colour))
//                .count(Materialized.with(Serdes.String(), Serdes.Long()))
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("CountsByColours")
                    .withKeySerde(Serdes.String())
                    .withValueSerde(Serdes.Long()))
                .toStream();

    }
}
