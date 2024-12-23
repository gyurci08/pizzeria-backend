package hu.jandzsogyorgy.pizzeriabackend.order;

import hu.jandzsogyorgy.pizzeriabackend.order.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.order.mapping.PizzaOrderMapper;
import hu.jandzsogyorgy.pizzeriabackend.order.repository.PizzaOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;
    private final PizzaOrderMapper pizzaOrderMapper;
    // list orders
    public List<PizzaOrderDto> listOrders(){
        return pizzaOrderMapper.toDto(
                pizzaOrderRepository.findAll()
        );
    }
    // load order
    // edit order
    // remove order
}
