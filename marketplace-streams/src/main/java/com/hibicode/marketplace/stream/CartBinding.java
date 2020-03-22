package com.hibicode.marketplace.stream;

import com.hibicode.marketplace.controller.cart.CartRequest;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface CartBinding {

    @Input("current_cart")
    KTable<String, CartRequest> currentCart();

    @Output
    KStream<String, String> output();
}
