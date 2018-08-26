package com.mkl.prez.technight;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mkl.prez.technight.Lambdas.*;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdasTest {
    private static final double EPSILON = 0.0001d;
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @Mock
    private DiscountDao discountDao;

    @Test
    public void testGetBasketCost() {
    }
}
