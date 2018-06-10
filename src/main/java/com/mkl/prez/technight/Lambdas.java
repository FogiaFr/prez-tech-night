package com.mkl.prez.technight;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Lambdas {


    public static void main(String... args) {
        List<ProductDto> products = createList();
    }

    static List<ProductDto> createList() {
        return Arrays.asList(new ProductDto(1L, "first"),
                new ProductDto(2L, "seconf"),
                new ProductDto(3L, "third"),
                new ProductDto(4L, "fourth"),
                new ProductDto(5L, "fifth"));
    }

    @Data
    static class ProductDto {
        private final Long id;
        private final String code;
    }
}
