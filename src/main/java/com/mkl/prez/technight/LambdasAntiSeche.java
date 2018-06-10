package com.mkl.prez.technight;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class LambdasAntiSeche {

    static void filter(List<Product> products, Predicate<Product> predicate) {
        products.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    static void filterALAncienne(List<Product> products, Filter filter) {
        for (Product product : products) {
            if (filter.filter(product)) {
                System.out.println(product);
            }
        }
    }

    public static void main(String... args) {
        List<Product> products = createList();

        filterALAncienne(products, new Filter() {
            @Override
            public boolean filter(Product product) {
                return product.getId() <= 3L;
            }
        });

        filter(products, new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getId() <= 3L;
            }
        });

        filter(products, p -> p.getId() <= 3L);

        Predicate<Product> pred = p -> p.getId() <= 3L;
        Filter filter = p -> p.getId() <= 3L;

        filter(products, pred);
//        filter(products, filter);
    }

    class ProductService {
        ProductDao productDao;

        Long getBasketCostVersion1() {
            List<Product> products = productDao.getProductsInBasket();

//            return products.stream()
//                    .map(Product::getPrice)
//                    .collect(Collectors.summingLong(price -> price));
            return products.stream()
                    .collect(Collectors.summingLong(Product::getPrice));
        }

        Long getBasketCostVersion2() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            Map<Long, Long> priceByProductIds = sections.stream()
                    .collect(Collectors.toMap(sp -> sp.getProduct().getId(), SectionPart::getPrice));

            return products.stream()
                    .map(product -> {
                        Long price = priceByProductIds.get(product.getId());
                        if (price == null) {
                            price = product.getPrice();
                        }
                        return price;
                    })
                    .collect(Collectors.summingLong(price -> price));
        }

        Double getBasketCost() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            Map<Long, Double> priceByProductIds = sections.stream()
                    .collect(Collectors.groupingBy(sp -> sp.getProduct().getId(),
                            Collectors.averagingLong(SectionPart::getPrice)));
            return products.stream()
                    .collect(Collectors.summingDouble(product -> priceByProductIds.get(product.getId())));
        }
    }

    interface ProductDao {
        List<Product> getProductsInBasket();

        List<SectionPart> getSectionOfBasket();
    }

    interface Filter {
        boolean filter(Product product);
    }

    static List<Product> createList() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Arrays.asList(new Product(1L, "first"),
                new Product(2L, "second"),
                new Product(3L, "third"),
                new Product(4L, "fourth"),
                new Product(5L, "fifth"));
    }

    @Data
    static class Product {
        private final Long id;
        private final String code;
        private LocalDateTime creationDate;
        private boolean activated;
        private Long price;
    }

    @Data
    static class SectionPart {
        private final Long id;
        private final Product product;
        private final Long price;
    }
}
