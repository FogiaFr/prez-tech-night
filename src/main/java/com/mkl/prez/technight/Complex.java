package com.mkl.prez.technight;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Complex {
    @Service
    static class ProductService {
        @Autowired
        ProductDomain productDomain;
        @Autowired
        ProductDao productDao;

        Double getBasketCost() {
            List<Product> products = productDao.getProductsInBasket();

            ToDoubleFunction<Product> getPrice = product -> {
                if (product.getType() == ProductTypeEnum.BOOK) {
                    return productDomain.getBookPrice(product.getPrice());
                }
                return product.getPrice();
            };

            return products.stream()
                    .collect(Collectors.summingDouble(getPrice));
        }
    }

    @Component
    static class ProductDomain {
        @Autowired
        ProductDao productDao;

        Double getBookPrice(Double price) {
            return productDao.getBookFactor() * price;
        }
    }

    interface ProductDao {
        List<Product> getProductsInBasket();

        Double getBookFactor();
    }

    @Data
    static class Product {
        private final Long id;
        private final String code;
        private LocalDateTime creationDate;
        private boolean activated;
        private final double price;
        private final ProductTypeEnum type;
    }

    enum ProductTypeEnum {
        VEGETABLE, HIFI, BOOK
    }
}
