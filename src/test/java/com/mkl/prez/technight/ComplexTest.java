package com.mkl.prez.technight;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mkl.prez.technight.Complex.*;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComplexTest {
    private static final double EPSILON = 0.0001d;
    @InjectMocks
    private ProductService productService;

    private ProductDomain productDomain;
    @Mock
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
}
