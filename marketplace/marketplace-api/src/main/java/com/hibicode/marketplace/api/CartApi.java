package com.hibicode.marketplace.api;

import com.hibicode.marketplace.api.dto.CartWeb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public interface CartApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody CartWeb cartWeb);
}
