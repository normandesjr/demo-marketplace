package com.hibicode.marketplace.api;

import com.hibicode.marketplace.api.dto.ProductWeb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/products")
public interface ProductApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void save(@Valid @RequestBody ProductWeb productWeb);

    @GetMapping("/{sku}")
    ResponseEntity<ProductWeb> getBySku(@PathVariable String sku);

}
