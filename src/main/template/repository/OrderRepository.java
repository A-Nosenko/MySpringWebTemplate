package template.repository;

import template.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUserId(int userId);

    void deleteOrdersByProductId(int productId);

    Order findOrderById(int orderId);

    @Modifying
    @Query("UPDATE Order a SET a.payDate = :date WHERE a.id = :orderId")
    void payOffTheOrder(@Param("orderId") int orderId,
                        @Param("date") Timestamp date);
}
