package org.dominwos.repository;

import java.util.List;

import org.dominwos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {

}
