package demo.bfims.Controllers.OrderControllers;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping("/orders/{customerid}")
    public List<OrderDto> getCustomerOrders(Long id){
        return orderService.getCustomerOrders(id);
    }

    @PostMapping("/order")
    public OrderDto newOrder(@RequestBody Order order){
        return orderService.newOrder(order);
    }

}
