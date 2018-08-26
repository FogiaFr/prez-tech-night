package com.mkl.prez.technight;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mkl.prez.technight.Complex.*;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ComplexAntiSecheTest {
    private static final double EPSILON = 0.0001d;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDomain productDomain;
    @Autowired
    private ProductDao productDao;

    @Test
    public void test() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1l, "tv", 12d, ProductTypeEnum.HIFI));
        products.add(new Product(2l, "lotr", 10d, ProductTypeEnum.BOOK));

        Mockito.when(productDao.getProductsInBasket()).thenReturn(products);
        Mockito.when(productDao.getBookFactor()).thenReturn(1.5d);

        Double price = productService.getBasketCost();

        Assert.assertEquals(27d, price, EPSILON);
    }

    @org.springframework.context.annotation.Configuration
    @ComponentScan
    static class Configuration {
        @Bean
        public ProductDao productDao() {
            return Mockito.mock(ProductDao.class);
        }
    }
}
