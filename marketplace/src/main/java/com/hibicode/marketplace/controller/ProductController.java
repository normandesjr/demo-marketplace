package com.hibicode.marketplace.controller;

import com.hibicode.marketplace.controller.dto.ProductDTO;
import com.hibicode.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ProductDTO productDTO) {
        productRepository.save(productDTO.toProduct());
    }

    @GetMapping("/{sku}")
    public ProductDTO getBySku(@PathVariable String sku) {
        return new ProductDTO(productRepository.findBySku(sku));
    }

}
