package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity.MenuItem;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity.OrderItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends ListCrudRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);
    List<OrderItem> findAllByOrderIdIn(List<Long> orderIds);
}
