package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDto> getAllOrders(){
        List<Order> orders =  orderRepo.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    public OrderDto getOrder(Long id){
        return modelMapper.map(orderRepo.findById(id).orElse(null), OrderDto.class);
    }

    public List<OrderDto> getCustomerOrders(Long id){
        return orderRepo.getOrdersByCustomerId(id)
                .stream().map(order -> modelMapper.map(order,OrderDto.class)).toList();
    }

    //needs to add order to customer without creating new customer
    //only create new customer if not signed in(email w/o id maybe?)
    public OrderDto newOrder(Order order){
        Customer customer = customerRepo.getCustomerByEmail(order.getCustomer().getEmail()).orElse(null);
        order.setCustomer(customer);

        return modelMapper.map(orderRepo.save(order),OrderDto.class);
    }
}
