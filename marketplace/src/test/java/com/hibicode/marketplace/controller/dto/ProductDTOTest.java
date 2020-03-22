package com.hibicode.marketplace.controller.dto;

import com.hibicode.marketplace.controller.dto.builder.ProductDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "A", "AA", "AAA", "AAAB", "AAABBB"})
    public void should_create_constraint_violation_for_invalid_sku(String sku) {
        ProductDTO productDTO = ProductDTOBuilder.create()
                .withName("MacBook Pro")
                .withPrice(new BigDecimal("1999.99"))
                .withSku(sku)
                .build();

        assertWithOneConstraint(productDTO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "A", "AA", "AAABBBCCCDDDEEEF"})
    public void should_create_constraint_violation_for_invalid_name(String name) {
        ProductDTO productDTO = ProductDTOBuilder.create()
                .withName(name)
                .withPrice(new BigDecimal("1999.99"))
                .withSku("AAA111")
                .build();

        assertWithOneConstraint(productDTO);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "-1")
    public void should_create_constraint_violation_for_invalid_price(BigDecimal price) {
        ProductDTO productDTO = ProductDTOBuilder.create()
                .withName("MacBook Pro")
                .withPrice(price)
                .withSku("AAA111")
                .build();

        assertWithOneConstraint(productDTO);
    }

    private void assertWithOneConstraint(ProductDTO productDTO) {
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertEquals(1, violations.size());
    }

}