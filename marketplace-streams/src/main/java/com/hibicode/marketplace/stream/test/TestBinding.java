package com.hibicode.marketplace.stream.test;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface TestBinding {

    @Input
    KTable<String, String> input();

    @Output("output2")
    KStream<String, Long> output2();

}
