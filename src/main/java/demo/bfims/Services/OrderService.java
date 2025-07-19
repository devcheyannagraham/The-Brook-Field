package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Order.*;
import demo.bfims.Enums.TransactionType;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.OrderRepo;
import demo.bfims.Repo.TransactionRepo;
import jakarta.persistence.EntityManager;
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
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    private EntityManager entityManager;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    public OrderDto getOrder(Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            return modelMapper.map(order, OrderDto.class);
        }
        return null;
    }

    public List<OrderDto> getCustomerOrders(Long id) {
        return orderRepo.getOrdersByCustomerId(id)
                .stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {
        System.out.println("OrderDto newOrder: " + orderDto);

        // Get Customer
        Customer customer = modelMapper.map(orderDto.getCustomer(), Customer.class);

        if (customer.getId() == null) {
            // Check if customer exists by email
            Customer existingCustomer = customerRepo.getCustomerByEmail(orderDto.getCustomer().getEmail()).orElse(null);
            if (existingCustomer != null) {
                customer = existingCustomer;
            }
        }
        // If the Customer exists (has an ID), merge it; otherwise, persist it.
        // The merge() method handles both cases:
        // - If customer is transient (new), it becomes persistent.
        // - If customer is detached (existing), it's re-attached and updated.
        Customer managedCustomer = entityManager.merge(modelMapper.map(customer, Customer.class));
        orderDto.setCustomer(modelMapper.map(managedCustomer, CustomerDto.class));

        Order savedOrder = orderRepo.save(modelMapper.map(orderDto, Order.class));
        return modelMapper.map(savedOrder, OrderDto.class);
    }
}
