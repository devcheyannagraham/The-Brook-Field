package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        System.out.println("Order List: " + orders);
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    public OrderDto getOrder(Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        System.out.println("Order: " + order);
        if (order != null) {
            return modelMapper.map(order, OrderDto.class);
        }
        return null;
    }

    public List<OrderDto> getCustomerOrders(Long id) {
        return orderRepo.getOrdersByCustomerId(id)
                .stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    //needs to add order to customer without creating new customer
    //only create new customer if not signed in(email w/o id maybe?)
    @Transactional
    public OrderDto newOrder(Order order) {
        Customer customer = customerRepo.getCustomerByEmail(order.getCustomer().getEmail()).orElse(null);
        if (customer != null) {
            order.getCustomer().setId(customer.getId());
        }
        return modelMapper.map(orderRepo.save(order), OrderDto.class);
    }
}
