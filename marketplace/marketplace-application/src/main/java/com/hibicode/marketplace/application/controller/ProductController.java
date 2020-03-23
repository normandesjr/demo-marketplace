package com.hibicode.marketplace.application.controller;

import com.hibicode.marketplace.api.ProductApi;
import com.hibicode.marketplace.api.dto.ProductWeb;
import com.hibicode.marketplace.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ProductController implements ProductApi {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ProductWeb productWeb) {
        productService.save(productWeb.toProduct());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductWeb> getBySku(@PathVariable String sku) {
        return productService.findBySku(sku)
                .map(product -> ok(new ProductWeb(product)))
                .orElse(notFound().build());
    }

}
