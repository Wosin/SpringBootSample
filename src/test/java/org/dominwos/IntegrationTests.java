package org.dominwos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.dominwos.app.Application;
import org.dominwos.model.Order;
import org.dominwos.model.Product;
import org.dominwos.repository.OrdersRepository;
import org.dominwos.repository.ProductsRepository;
import org.dominwos.service.OrdersService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class IntegrationTests {

    private static final long FIRST_PRODUCT_ID = 1;
    private static final long SECOND_PRODUCT_ID = 2;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrdersService ordersService;

    @Test
    public void OrderCreationHappyPathTest(){
        List<Long> ids = new ArrayList<Long>();
        ids.add(FIRST_PRODUCT_ID);
        ids.add(SECOND_PRODUCT_ID);
        Optional<Order> orderOptional = ordersService.createNewOrder(ids);

        Assert.assertTrue(orderOptional.isPresent());
        Order order = orderOptional.get();
        Assert.assertEquals(order.getOrderedProducts().size(), 2);
        Assert.assertEquals(order.getTotalPrice().intValue(), 110);

        productsRepository.findAll().forEach( product -> Assert.assertFalse(product.isAvailable()));
        List<Order> allOrders = ordersRepository.findAll();

        Assert.assertEquals(allOrders.size(),1);
    }

    @Test
    public void OrderCreationWithNotExistingProductTest(){
        Optional<Order> orderOptional = ordersService.createNewOrder(Collections.singletonList(Long.valueOf(5)));
        Assert.assertFalse(orderOptional.isPresent());
    }

    @Before
    public void setUp(){
        Product firstProduct = new Product();
        firstProduct.setName("First Product");
        firstProduct.setId(FIRST_PRODUCT_ID);
        firstProduct.setPrice(BigDecimal.valueOf(100));
        Product secondProduct = new Product();
        secondProduct.setName("Second Product");
        secondProduct.setId(SECOND_PRODUCT_ID);
        secondProduct.setPrice(BigDecimal.valueOf(10));
        productsRepository.save(firstProduct);
        productsRepository.save(secondProduct);

    }

    @After
    public void cleanup() {
        ordersRepository.deleteAll();
        productsRepository.deleteAll();
    }
}
