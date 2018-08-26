package com.mkl.prez.technight;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mkl.prez.technight.LambdasAntiSeche.*;
import static org.mockito.Mockito.when;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdasTestAntiSeche {
    private static final double EPSILON = 0.0001d;
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @Mock
    private DiscountDao discountDao;

    @Test
    public void testGetBasketCostVersion1() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion1(), EPSILON);

        product = new Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion1(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion2() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion2(), EPSILON);

        product = new Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion2(), EPSILON);

        List<SectionPart> sections = new ArrayList<>();
        SectionPart section = new SectionPart(101L, 2L, 5d);
        sections.add(section);

        when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion2(), EPSILON);

        section = new SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion2(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion3() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion3(), EPSILON);

        product = new Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion3(), EPSILON);

        List<SectionPart> sections = new ArrayList<>();
        SectionPart section = new SectionPart(101L, 2L, 5d);
        sections.add(section);

        when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion3(), EPSILON);

        section = new SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion3(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion4() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion4(), EPSILON);

        product = new Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion4(), EPSILON);

        List<SectionPart> sections = new ArrayList<>();
        SectionPart section = new SectionPart(101L, 2L, 5d);
        sections.add(section);

        when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion4(), EPSILON);

        section = new SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion4(), EPSILON);

        List<Discount> discounts = new ArrayList<>();
        Discount discount = new Discount(ProductTypeEnum.BOOK);
        discount.setDiscountFlat(1d);
        discounts.add(discount);
        product.setType(ProductTypeEnum.BOOK);

        when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion4(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion4(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion5() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1L, "toto");
        product.setPrice(12d);
        products.add(product);

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion5(), EPSILON);

        product = new Product(2L, "tata");
        product.setPrice(3d);
        products.add(product);

        Assert.assertEquals(15d, productService.getBasketCostVersion5(), EPSILON);

        List<SectionPart> sections = new ArrayList<>();
        SectionPart section = new SectionPart(101L, 2L, 5d);
        sections.add(section);

        when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        section = new SectionPart(102L, 666L, 999d);
        sections.add(section);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        List<Discount> discounts = new ArrayList<>();
        Discount discount = new Discount(ProductTypeEnum.BOOK);
        discount.setDiscountFlat(1d);
        discounts.add(discount);
        product.setType(ProductTypeEnum.BOOK);

        when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
    }

    @Test
    public void testGetBasketCostVersion5Style() {
        List<Product> products = new ArrayList<>();
        products.add(createProduct(1l, "toto", 12d, null));

        when(productDao.getProductsInBasket()).thenReturn(products);

        Assert.assertEquals(12d, productService.getBasketCostVersion5(), EPSILON);

        products.add(createProduct(2L, "tata", 3d, ProductTypeEnum.BOOK));

        Assert.assertEquals(15d, productService.getBasketCostVersion5(), EPSILON);

        List<SectionPart> sections = new ArrayList<>();
        sections.add(createSection(101L, 2L, 5d));

        when(productDao.getSectionOfBasket()).thenReturn(sections);

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        sections.add(createSection(102L, 666L, 999d));

        Assert.assertEquals(17d, productService.getBasketCostVersion5(), EPSILON);

        List<Discount> discounts = new ArrayList<>();
        Discount discount = createDiscount(ProductTypeEnum.BOOK, 1d, null);
        discounts.add(discount);

        when(discountDao.getDiscounts()).thenReturn(discounts);

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        discount.setDiscountPercentage(0.1d);

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
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
                .addProduct(ProductBuilder.create().id(2l).price(3d).type(ProductTypeEnum.BOOK).product())
                .addSection(createSection(101L, 2L, 5d))
                .addSection(createSection(102L, 666L, 999d))
                .addDiscount(createDiscount(ProductTypeEnum.BOOK, 1d, null))
                .build();

        Assert.assertEquals(16d, productService.getBasketCostVersion5(), EPSILON);

        BasketCostBuilder.create(productDao, discountDao)
                .addProduct(ProductBuilder.create().id(1l).price(12d).product())
                .addProduct(ProductBuilder.create().id(2l).price(3d).type(ProductTypeEnum.BOOK).product())
                .addSection(createSection(101L, 2L, 5d))
                .addSection(createSection(102L, 666L, 999d))
                .addDiscount(createDiscount(ProductTypeEnum.BOOK, 1d, 0.1d))
                .build();

        Assert.assertEquals(15.5d, productService.getBasketCostVersion5(), EPSILON);
    }

    private static class BasketCostBuilder {
        ProductDao productDao;
        DiscountDao discountDao;
        List<Product> products = new ArrayList<>();
        List<SectionPart> sections = new ArrayList<>();
        List<Discount> discounts = new ArrayList<>();

        private BasketCostBuilder(ProductDao productDao, DiscountDao discountDao) {
            this.productDao = productDao;
            this.discountDao = discountDao;
        }

        static BasketCostBuilder create(ProductDao productDao, DiscountDao discountDao) {
            return new BasketCostBuilder(productDao, discountDao);
        }

        BasketCostBuilder addProduct(Product product) {
            products.add(product);
            return this;
        }

        BasketCostBuilder addSection(SectionPart sectionPart) {
            sections.add(sectionPart);
            return this;
        }

        BasketCostBuilder addDiscount(Discount discount) {
            discounts.add(discount);
            return this;
        }

        void build() {
            when(productDao.getProductsInBasket()).thenReturn(products);
            when(productDao.getSectionOfBasket()).thenReturn(sections);
            when(discountDao.getDiscounts()).thenReturn(discounts);
        }
    }

    private static class ProductBuilder {
        Long id;
        double price;
        ProductTypeEnum type;

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

        ProductBuilder type(ProductTypeEnum type) {
            this.type = type;
            return this;
        }

        Product product() {
            Product product = new Product(id, "osef");
            product.setPrice(price);
            product.setType(type);
            return product;
        }
    }

    @Test
    public void testGetBasketCostVersion5GWT() {
        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(12d);

        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .addProduct(ProductBuilderGWT.create().price(3d))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(15d);

        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .addProduct(ProductBuilderGWT.create().price(3d).withSection(SectionPartBuilderGWT.create().price(5d)))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(17d);

        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .addProduct(ProductBuilderGWT.create().price(3d).withSection(SectionPartBuilderGWT.create().price(5d)))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(17d);

        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .addProduct(ProductBuilderGWT.create().price(3d).type(ProductTypeEnum.BOOK).withSection(SectionPartBuilderGWT.create().price(5d)))
                .addDiscount(createDiscount(ProductTypeEnum.BOOK, 1d, null))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(16d);

        BasketCostBuilderGWT.create(productDao, discountDao)
                .addProduct(ProductBuilderGWT.create().price(12d))
                .addProduct(ProductBuilderGWT.create().price(3d).type(ProductTypeEnum.BOOK).withSection(SectionPartBuilderGWT.create().price(5d)))
                .addDiscount(createDiscount(ProductTypeEnum.BOOK, 1d, 0.1d))
                .whenGetBasketCostVersion5(productService)
                .thenResultShouldBe(15.5d);
    }

    private static class BasketCostBuilderGWT {
        static long cpt = 1;
        ProductDao productDao;
        DiscountDao discountDao;
        List<Product> products = new ArrayList<>();
        List<SectionPart> sections = new ArrayList<>();
        List<Discount> discounts = new ArrayList<>();
        Double result;

        private BasketCostBuilderGWT(ProductDao productDao, DiscountDao discountDao) {
            this.productDao = productDao;
            this.discountDao = discountDao;
        }

        static BasketCostBuilderGWT create(ProductDao productDao, DiscountDao discountDao) {
            return new BasketCostBuilderGWT(productDao, discountDao);
        }

        BasketCostBuilderGWT addProduct(ProductBuilderGWT productBuilder) {
            products.add(productBuilder.product(cpt));
            SectionPart sectionPart = productBuilder.sectionPart(cpt);
            if (sectionPart != null) {
                sections.add(sectionPart);
            }
            cpt++;
            return this;
        }

        BasketCostBuilderGWT addSection(SectionPart sectionPart) {
            sections.add(sectionPart);
            return this;
        }

        BasketCostBuilderGWT addDiscount(Discount discount) {
            discounts.add(discount);
            return this;
        }

        BasketCostBuilderGWT whenGetBasketCostVersion5(ProductService productService) {
            sections.add(createSection(102L, 666L, 999d));

            when(productDao.getProductsInBasket()).thenReturn(products);
            when(productDao.getSectionOfBasket()).thenReturn(sections);
            when(discountDao.getDiscounts()).thenReturn(discounts);

            result = productService.getBasketCostVersion5();

            return this;
        }

        void thenResultShouldBe(Double result) {
            Assert.assertEquals(result, this.result, EPSILON);
        }
    }

    private static class ProductBuilderGWT {
        double price;
        ProductTypeEnum type;
        SectionPartBuilderGWT sectionPartBuilder;

        static ProductBuilderGWT create() {
            return new ProductBuilderGWT();
        }

        ProductBuilderGWT price(double price) {
            this.price = price;
            return this;
        }

        ProductBuilderGWT type(ProductTypeEnum type) {
            this.type = type;
            return this;
        }

        ProductBuilderGWT withSection(SectionPartBuilderGWT sectionPartBuilder) {
            this.sectionPartBuilder = sectionPartBuilder;
            return this;
        }

        Product product(Long idProduct) {
            Product product = new Product(idProduct, "osef");
            product.setPrice(price);
            product.setType(type);
            return product;
        }

        SectionPart sectionPart(Long idProduct) {
            if (sectionPartBuilder == null) {
                return null;
            }

            return sectionPartBuilder.sectionPart(idProduct, idProduct);
        }
    }

    private static class SectionPartBuilderGWT {
        double price;

        static SectionPartBuilderGWT create() {
            return new SectionPartBuilderGWT();
        }

        SectionPartBuilderGWT price(double price) {
            this.price = price;
            return this;
        }

        SectionPart sectionPart(Long id, Long idProduct) {
            return new SectionPart(id, idProduct, price);
        }
    }

    private static Product createProduct(Long id, String code, Double price, ProductTypeEnum type) {
        Product product = new Product(id, code);
        product.setPrice(price);
        product.setType(type);
        return product;
    }

    private static SectionPart createSection(Long id, Long productId, Double price) {
        return new SectionPart(id, productId, price);
    }

    private static Discount createDiscount(ProductTypeEnum productType, Double flat, Double percent) {
        Discount discount = new Discount(productType);
        if (flat != null) {
            discount.setDiscountFlat(flat);
        }
        if (percent != null) {
            discount.setDiscountPercentage(percent);
        }
        return discount;
    }
}
