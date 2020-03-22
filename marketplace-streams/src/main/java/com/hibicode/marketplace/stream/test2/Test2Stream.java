package com.hibicode.marketplace.stream.test2;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Test2Binding.class)
public class Test2Stream {

    @StreamListener
    @SendTo("out2")
    public KStream<String, String> handleTest(@Input("in2") KTable<String, String> in2) {
        return in2
                .mapValues(value -> value.toUpperCase())
                .toStream();

    }

}
