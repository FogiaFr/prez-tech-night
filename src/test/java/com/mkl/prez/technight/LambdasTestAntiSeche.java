package com.mkl.prez.technight;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdasTestAntiSeche {
    private static final double EPSILON = 0.0001d;
    @InjectMocks
    private LambdasAntiSeche.ProductService productService;

    @Mock
    private LambdasAntiSeche.ProductDao productDao;

    @Mock
    private LambdasAntiSeche.DiscountDao discountDao;

    @Test
    public void testGetBasketCostVersion1() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion1(), EPSILON);

        product = new LambdasAntiSeche.Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion1(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion2() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion2(), EPSILON);

        product = new LambdasAntiSeche.Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion2(), EPSILON);

        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        LambdasAntiSeche.SectionPart section = new LambdasAntiSeche.SectionPart(101L, 2L, 5d);
        sections.add(section);

        Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion2(), EPSILON);

        section = new LambdasAntiSeche.SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion2(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion3() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion3(), EPSILON);

        product = new LambdasAntiSeche.Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion3(), EPSILON);

        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        LambdasAntiSeche.SectionPart section = new LambdasAntiSeche.SectionPart(101L, 2L, 5d);
        sections.add(section);

        Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion3(), EPSILON);

        section = new LambdasAntiSeche.SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion3(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion4() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion4(), EPSILON);

        product = new LambdasAntiSeche.Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion4(), EPSILON);

        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        LambdasAntiSeche.SectionPart section = new LambdasAntiSeche.SectionPart(101L, 2L, 5d);
        sections.add(section);

        Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion4(), EPSILON);

        section = new LambdasAntiSeche.SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion4(), EPSILON);

        List<LambdasAntiSeche.Discount> discounts = new ArrayList<>();
        LambdasAntiSeche.Discount discount = new LambdasAntiSeche.Discount(LambdasAntiSeche.ProductTypeEnum.BOOK);
        discount.setDiscountFlat(1d);
        discounts.add(discount);
        product.setType(LambdasAntiSeche.ProductTypeEnum.BOOK);

        Mockito.when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion4(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion4(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion5() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion5(), EPSILON);

        product = new LambdasAntiSeche.Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion5(), EPSILON);

        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        LambdasAntiSeche.SectionPart section = new LambdasAntiSeche.SectionPart(101L, 2L, 5d);
        sections.add(section);

        Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        section = new LambdasAntiSeche.SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        List<LambdasAntiSeche.Discount> discounts = new ArrayList<>();
        LambdasAntiSeche.Discount discount = new LambdasAntiSeche.Discount(LambdasAntiSeche.ProductTypeEnum.BOOK);
        discount.setDiscountFlat(1d);
        discounts.add(discount);
        product.setType(LambdasAntiSeche.ProductTypeEnum.BOOK);

        Mockito.when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion5Style() {
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        products.add(createProduct(1l, "toto", 12d, null));

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion5(), EPSILON);

        products.add(createProduct(2L, "tata", 3d, LambdasAntiSeche.ProductTypeEnum.BOOK));

        Assert.assertEquals(15d, productService.getBasketCostVersion5(), EPSILON);

        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        sections.add(createSection(101L, 2L, 5d));

        Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        sections.add(createSection(102L, 666L, 999d));

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        List<LambdasAntiSeche.Discount> discounts = new ArrayList<>();
        LambdasAntiSeche.Discount discount = createDiscount(LambdasAntiSeche.ProductTypeEnum.BOOK, 1d, null);
        discounts.add(discount);

        Mockito.when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
    }

    private static LambdasAntiSeche.Product createProduct(Long id, String code, Double price, LambdasAntiSeche.ProductTypeEnum type) {
        LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(id, code);
        product.setPrice(price);
        product.setType(type);
        return product;
    }

    private static LambdasAntiSeche.SectionPart createSection(Long id, Long productId, Double price) {
        return new LambdasAntiSeche.SectionPart(id, productId, price);
    }

    private static LambdasAntiSeche.Discount createDiscount(LambdasAntiSeche.ProductTypeEnum productType, Double flat, Double percent) {
        LambdasAntiSeche.Discount discount = new LambdasAntiSeche.Discount(productType);
        if (flat != null) {
            discount.setDiscountFlat(flat);
        }
        if (percent != null) {
            discount.setDiscountPercentage(percent);
        }
        return discount;
    }

    @Test
    public void testGetBasketCostVersion5Swag() {
        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .build();

        Assert.assertEquals(12d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).product())
                .build();

        Assert.assertEquals(15d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).product())
                .addSection(createSection(101L, 2L, 5d))
                .build();

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).product())
                .addSection(createSection(101L, 2L, 5d))
                .addSection(createSection(102L, 666L, 999d))
                .build();

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).type(LambdasAntiSeche.ProductTypeEnum.BOOK).product())
                .addSection(createSection(101L, 2L, 5d))
                .addSection(createSection(102L, 666L, 999d))
                .addDiscount(createDiscount(LambdasAntiSeche.ProductTypeEnum.BOOK, 1d, null))
                .build();

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).type(LambdasAntiSeche.ProductTypeEnum.BOOK).product())
                .addSection(createSection(101L, 2L, 5d))
                .addSection(createSection(102L, 666L, 999d))
                .addDiscount(createDiscount(LambdasAntiSeche.ProductTypeEnum.BOOK, 1d, 0.1d))
                .build();

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
    }

    private static class BasketCostBuilder {
        LambdasAntiSeche.ProductDao productDao;
        LambdasAntiSeche.DiscountDao discountDao;
        List<LambdasAntiSeche.Product> products = new ArrayList<>();
        List<LambdasAntiSeche.SectionPart> sections = new ArrayList<>();
        List<LambdasAntiSeche.Discount> discounts = new ArrayList<>();

        private BasketCostBuilder(LambdasAntiSeche.ProductDao productDao, LambdasAntiSeche.DiscountDao discountDao) {
            this.productDao = productDao;
            this.discountDao = discountDao;
        }

        static BasketCostBuilder create(LambdasAntiSeche.ProductDao productDao, LambdasAntiSeche.DiscountDao discountDao) {
            return new BasketCostBuilder(productDao, discountDao);
        }

        BasketCostBuilder addProduct(LambdasAntiSeche.Product product) {
            products.add(product);
            return this;
        }

        BasketCostBuilder addSection(LambdasAntiSeche.SectionPart sectionPart) {
            sections.add(sectionPart);
            return this;
        }

        BasketCostBuilder addDiscount(LambdasAntiSeche.Discount discount) {
            discounts.add(discount);
            return this;
        }

        void build() {
            Mockito.when(productDao.getProductsInBasket()).thenReturn(products);
            Mockito.when(productDao.getSectionOfBasket()).thenReturn(sections);
            Mockito.when(discountDao.getDiscounts()).thenReturn(discounts);
        }
    }

    private static class ProductBuilder {
        Long id;
        double price;
        LambdasAntiSeche.ProductTypeEnum type;

        static ProductBuilder create() {
            return new ProductBuilder();
        }

        ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        ProductBuilder type(LambdasAntiSeche.ProductTypeEnum type) {
            this.type = type;
            return this;
        }

        LambdasAntiSeche.Product product() {
            LambdasAntiSeche.Product product = new LambdasAntiSeche.Product(id, "osef");
            product.setPrice(price);
            product.setType(type);
            return product;
        }
    }
}
