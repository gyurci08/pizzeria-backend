package hu.jandzsogyorgy.pizzeriabackend.pizzeria.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.service.CustomerService;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity.PizzaOrder;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.map.PizzaOrderMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.service.PizzaOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PizzeriaService {
    private final PizzaOrderService pizzaOrderService;
    private final PizzaOrderMapper pizzaOrderMapper;

    private final CustomerService customerService;



    public List<PizzaOrderDto> listOrders(){
        return pizzaOrderService.listOrders();
    }
    public PizzaOrderDto createOrder(PizzaOrderSaveDto dto){
        return pizzaOrderService.createOrder(dto);
    }

}
