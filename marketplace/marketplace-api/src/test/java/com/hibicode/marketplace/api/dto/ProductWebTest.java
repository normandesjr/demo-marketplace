package com.hibicode.marketplace.api.dto;

import com.hibicode.marketplace.api.dto.builder.ProductWebBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductWebTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "A", "AA", "AAA", "AAAB", "AAABBB"})
    public void should_create_constraint_violation_for_invalid_sku(String sku) {
        var productWeb = ProductWebBuilder.create()
                .withName("MacBook Pro")
                .withPrice(new BigDecimal("1999.99"))
                .withSku(sku)
                .build();

        assertWithOneConstraint(productWeb);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "A", "AA", "AAABBBCCCDDDEEEF"})
    public void should_create_constraint_violation_for_invalid_name(String name) {
        var ProductWeb = ProductWebBuilder.create()
                .withName(name)
                .withPrice(new BigDecimal("1999.99"))
                .withSku("AAA111")
                .build();

        assertWithOneConstraint(ProductWeb);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "-1")
    public void should_create_constraint_violation_for_invalid_price(BigDecimal price) {
        ProductWeb ProductWeb = ProductWebBuilder.create()
                .withName("MacBook Pro")
                .withPrice(price)
                .withSku("AAA111")
                .build();

        assertWithOneConstraint(ProductWeb);
    }

    private void assertWithOneConstraint(ProductWeb ProductWeb) {
        Set<ConstraintViolation<ProductWeb>> violations = validator.validate(ProductWeb);
        assertEquals(1, violations.size());
    }

}