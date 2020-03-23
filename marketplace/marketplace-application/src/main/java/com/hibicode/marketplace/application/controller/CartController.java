package com.hibicode.marketplace.application.controller;

import com.hibicode.marketplace.api.CartApi;
import com.hibicode.marketplace.api.dto.CartWeb;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController implements CartApi {

    @Override
    public void create(CartWeb cartWeb) {

    }

}
