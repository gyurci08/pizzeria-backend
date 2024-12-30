package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.service.CustomerService;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity.PizzaOrder;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.map.PizzaOrderMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.map.PizzaOrderSaveMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.repository.PizzaOrderRepository;
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
    private final PizzaOrderSaveMapper pizzaOrderSaveMapper;

    private final CustomerService customerService;

    private PizzaOrderDto mapCustomerNameToPizzaOrderDto(PizzaOrder po){
        String customerName = customerService.loadCustomer(po.getCustomerId()).name();
        return new PizzaOrderDto(
                po.getId(),
                po.getCustomerId(),
                customerName,
                po.getOrderDate(),
                po.getTotalAmount(),
                po.getStatus()
        );
    }



    // list orders
    public List<PizzaOrderDto> listOrders(){
        return pizzaOrderRepository.findAll().stream().map(this::mapCustomerNameToPizzaOrderDto).toList();
    }
    // load order

    //new order
    public PizzaOrderDto createOrder(PizzaOrderSaveDto dto){
        return pizzaOrderMapper.toDto(
                pizzaOrderRepository.save(
                    pizzaOrderSaveMapper.toEntity(dto)
                )
        );
    }

    // edit order
    // remove order
}
