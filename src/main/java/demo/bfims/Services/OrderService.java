package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Entities.Order.*;
import demo.bfims.Enums.ItemOrderType;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.OrderRepo;
import demo.bfims.Repo.TransactionRepo;
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
    private ItemRepo itemRepo;

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
    public OrderDto newOrder(OrderDto order) {
        System.out.println("Order: " + order);
        Customer customer;

//        Get Customer
        if (order.getCustomer().getId() == null) {
//            // Check if customer exists by email
            customer = customerRepo.getCustomerByEmail(order.getCustomer().getEmail()).orElse(null);
        } else {
            //ID provided
            customer = customerRepo.findById(order.getCustomer().getId()).orElse(null);
        }
        if (customer != null) {
            order.setCustomer(modelMapper.map(customer, CustomerDto.class));
        }

        // save rentals and purchases for reporting and records
        order.getOrderItems().forEach(orderItem -> {
            //Get item and update it's state
            Transaction trans = null;
            System.out.println("ORDER TYPE " + orderItem.getItemOrderType());
            if (orderItem.getItemOrderType().equals(ItemOrderType.RENTAL)) {
                //Or add entry to rental records
                trans = new Rental();
            }
            // Add entry to purchase records
            else if (orderItem.getItemOrderType().equals(ItemOrderType.PURCHASE)) {
                trans = new Purchase();
            }
            if (trans != null) {
                transactionRepo.save(trans);
            }
        });

        Order savedOrder = orderRepo.save(modelMapper.map(order, Order.class));
        return modelMapper.map(savedOrder, OrderDto.class);
    }
}
