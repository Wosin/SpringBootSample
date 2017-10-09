package org.dominwos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.dominwos.controller.Controller;
import org.dominwos.model.Order;
import org.dominwos.model.Product;
import org.dominwos.repository.OrdersRepository;
import org.dominwos.repository.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    public static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductsRepository productsRepository;

    public Optional<Order> createNewOrder(List<Long> ids) {
        List<Product> productsDetails = productsRepository.getAvailableProductsForIds(ids);
        BigDecimal totalPrice = productsDetails.stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setOrderedProducts(productsDetails);
        order.setTotalPrice(totalPrice);

        if(productsDetails.isEmpty()) {
            log.warn("List of ordered products is empty or none of the products is available! Order will not be saved in DB!");
            return Optional.empty();
        } else if(productsDetails.size() != ids.size()) {
            log.debug("One or more products from orderList are not available, adding all available products to Order.");
        }

        productsDetails.forEach(product -> product.setAvailable(false));
        productsRepository.save(productsDetails);
        ordersRepository.save(order);

        return Optional.of(order);
    }
}
