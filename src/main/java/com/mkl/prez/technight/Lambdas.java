package com.mkl.prez.technight;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Lambdas {


    public static void main(String... args) {
        /**
         Thanks to Victor Rentea, Mario Fusco and Rémi Forax for their devoxx !
         */
        List<Product> products = createList();
    }

    static List<Product> createList() {
        return Arrays.asList(new Product(1L, "first"),
                new Product(2L, "seconf"),
                new Product(3L, "third"),
                new Product(4L, "fourth"),
                new Product(5L, "fifth"));
    }

    static class ProductService {
        ProductDao productDao;
        DiscountDao discountDao;

        Double getBasketCost() {
            return null;
        }
    }

    interface ProductDao {
        List<Product> getProductsInBasket();

        List<SectionPart> getSectionOfBasket();
    }

    interface DiscountDao {
        List<Discount> getDiscounts();
    }

    @Data
    static class Product {
        private final Long id;
        private final String code;
        private LocalDateTime creationDate;
        private boolean activated;
        private double price;
        private ProductTypeEnum type;
    }

    enum ProductTypeEnum {
        VEGETABLE, HIFI, BOOK
    }

    @Data
    static class SectionPart {
        private final Long id;
        private final Long productId;
        private final double price;
    }

    @Data
    static class Discount {
        private final ProductTypeEnum productType;
        private double discountFlat;
        private double discountPercentage;
    }
}
