package com.hibicode.marketplace.service;

import com.hibicode.marketplace.domain.FirstCart;
import com.hibicode.marketplace.domain.service.CartService;

public class CartServiceImpl implements CartService {

//    @Value("${kafka.producer.current_cart.topic}")
//    private String currentCartTopic;

    @Override
    public void create(FirstCart firstCart) {
//        productRepository.findBySku(cartCreateRequest.getSku())
//                .map(cartCreateRequest::updateTotal)
//                .orElseThrow(NotFoundException::new);
//
        // TODO: Ta com um problema estranho, se o endereco do broker esta errado ele nao retorna excecao. Como configurar o timeout?
//        kafkaTemplate.send(currentCartTopic, cartCreateRequest.getId(), cartCreateRequest);
    }
}
