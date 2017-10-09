package org.dominwos.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.dominwos.model.Order;
import org.dominwos.model.Product;
import org.dominwos.repository.OrdersRepository;
import org.dominwos.repository.ProductsRepository;
import org.dominwos.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wosin on 08.10.2017.
 */
@RestController
public class Controller {

    public static final Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrdersService ordersService;

    @RequestMapping(value = "/liveness")
    public String livenessProbe(){
        return "OK";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        return productsRepository.save(product);

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productsRepository.getAllAvailableProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") long id){
        return productsRepository.findOne(id);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order addOrder(@RequestBody List<Long> productsIds) {
        Optional<Order> createdOrder = ordersService.createNewOrder(productsIds);
        if(!createdOrder.isPresent()) {
            log.warn("New order was not created!");
        }

        return createdOrder.orElse(new Order());
    }

}
