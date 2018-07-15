package com.mkl.prez.technight;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class LambdasTestAntiSeche {
    @InjectMocks
    private LambdasAntiSeche.ProductService productService;

    @Mock
    private LambdasAntiSeche.ProductDao productDao;

    @Mock
    private LambdasAntiSeche.DiscountDao discountDao;

    @Test
    public void testGetPrice() {

    }
}
