package template.service.contract;

import template.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface OrderService {
    List<Order> findOrdersByUserId(int userId);

    void remove(int orderId);

    @Transactional
    void removeAll(int productId);

    Order getOrder(int orderId);

    void addOrder(Order order);

    @Transactional
    void payOffTheOrder(int orderId, Timestamp date);
}
