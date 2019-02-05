package template.service;

import template.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import template.repository.OrderRepository;
import template.service.contract.OrderService;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findOrdersByUserId(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public void remove(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void removeAll(int productId) {
        orderRepository.deleteOrdersByProductId(productId);
    }

    @Override
    public void payOffTheOrder(int orderId, Timestamp date) {
        orderRepository.payOffTheOrder(orderId, date);
    }
}
