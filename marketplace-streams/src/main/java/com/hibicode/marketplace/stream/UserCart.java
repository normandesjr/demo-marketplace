package com.hibicode.marketplace.stream;

import com.hibicode.marketplace.controller.cart.CartRequest;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(CartBinding.class)
public class UserCart {

    @StreamListener
    @SendTo("output")
    public KStream<String, String> handleCart(@Input("current_cart") KTable<String, CartRequest> currentCart) {
        return currentCart
                .mapValues(cart -> cart.getClientId())
                .toStream();

    }

}
