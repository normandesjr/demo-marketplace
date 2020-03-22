package com.hibicode.marketplace.controller;

import com.hibicode.marketplace.controller.cart.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private KafkaTemplate<String, CartRequest> kafkaTemplate;

    @Value("${kafka.producer.current_cart.topic}")
    private String currentCartTopic;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CartRequest cartRequest) {
        // TODO: Ta com um problema estranho, se o endereco do broker esta errado ele nao retorna excecao.
        kafkaTemplate.send(currentCartTopic, cartRequest.getClientId(), cartRequest);
    }

}
