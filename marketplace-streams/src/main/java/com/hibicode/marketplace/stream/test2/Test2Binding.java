package com.hibicode.marketplace.stream.test2;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface Test2Binding {

    @Input("in2")
    KTable<String, String> in2();

    @Output("out2")
    KStream<String, String> out2();

}
