package com.mkl.prez.technight;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class LambdasAntiSeche {

    interface Filter {
        boolean filter(Product product);
    }

    static List<Product> createList() {
        return Arrays.asList(new Product(1L, "first"),
                new Product(2L, "second"),
                new Product(3L, "third"),
                new Product(4L, "fourth"),
                new Product(5L, "fifth"));
    }

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
        filterALAncienne(products, p -> p.getId() <= 3L);

        Predicate<Product> pred = p -> p.getId() <= 3L;
        Filter filter = p -> p.getId() <= 3L;

        filter(products, pred);
//        filter(products, filter);
    }

    static class ProductService {
        ProductDao productDao;
        DiscountDao discountDao;

        Double getBasketCostVersion1() {
            List<Product> products = productDao.getProductsInBasket();

//            return products.stream()
//                    .map(Product::getPrice)
//                    .collect(Collectors.summingLong(price -> price));
            return products.stream()
                    .collect(Collectors.summingDouble(Product::getPrice));
        }

        Double getBasketCostVersion2() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            Map<Long, Double> priceByProductIds = sections.stream()
                    .collect(Collectors.toMap(SectionPart::getProductId, SectionPart::getPrice));

            return products.stream()
                    .collect(Collectors.summingDouble(product -> {
                        Double price = priceByProductIds.get(product.getId());
                        if (price == null) {
                            price = product.getPrice();
                        }
                        return price;
                    }));
        }

        Double getBasketCostVersion3() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            Map<Long, Double> priceByProductIds = sections.stream()
                    .collect(Collectors.toMap(SectionPart::getProductId, SectionPart::getPrice));

            return products.stream()
                    .collect(Collectors.summingDouble(product -> getPrice(product, priceByProductIds)));
        }

        /**
         * @param product           whose we want the price.
         * @param priceByProductIds Map of id of product that have a special price.
         * @return the price of the product.
         */
        private Double getPrice(Product product, Map<Long, Double> priceByProductIds) {
            Double price = priceByProductIds.get(product.getId());
            if (price == null) {
                price = product.getPrice();
            }
            return price;
        }

        private Double getPrice2(Product product, Map<Long, Double> priceByProductIds) {
            return Optional.ofNullable(priceByProductIds.get(product.getId())).orElse(product.getPrice());
        }

        private Double getPrice3(Product product, Map<Long, Double> priceByProductIds, List<Discount> discounts) {
            Double price = Optional.ofNullable(priceByProductIds.get(product.getId())).orElse(product.getPrice());

            Double discountAmount = discounts.stream()
                    .filter(discount -> product.getType() == discount.getProductType())
                    .collect(Collectors.summingDouble(discount -> discount.getDiscountFlat() + price * discount.getDiscountPercentage()));

            return price - discountAmount;
        }

        Double getBasketCostVersion4() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            List<Discount> discounts = discountDao.getDiscounts();
            Map<Long, Double> priceByProductIds = sections.stream()
                    .collect(Collectors.toMap(SectionPart::getProductId, SectionPart::getPrice));

            return products.stream()
                    .collect(Collectors.summingDouble(product -> getPrice3(product, priceByProductIds, discounts)));
        }

        Double getBasketCostVersion5() {
            List<Product> products = productDao.getProductsInBasket();
            List<SectionPart> sections = productDao.getSectionOfBasket();
            List<Discount> discounts = discountDao.getDiscounts();
            Map<Long, Double> priceByProductIds = sections.stream()
                    .collect(Collectors.toMap(SectionPart::getProductId, SectionPart::getPrice));
            BiFunction<Product, Double, Double> getDiscount = (product, price) -> discounts.stream()
                    .filter(discount -> product.getType() == discount.getProductType())
                    .collect(Collectors.summingDouble(discount -> discount.getDiscountFlat() + price * discount.getDiscountPercentage()));

            ToDoubleFunction<Product> getPrice = product -> {
                Double price = Optional.ofNullable(priceByProductIds.get(product.getId())).orElse(product.getPrice());

                Double discountAmount = getDiscount.apply(product, price);

                return price - discountAmount;
            };

            return products.stream()
                    .collect(Collectors.summingDouble(getPrice));
        }

        Double getBasketCost() {
            return getBasketCostVersion5();
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
