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


        // Handle order items
//        order.getOrderItems().forEach(orderItem -> {
//            System.out.println("ORDER ITEM: " + orderItem.getItemOrderType());
//            Double total = 0.0;
//            Item item = itemRepo.findById(orderItem.getItem().getItemId()).orElse(null);
//            System.out.println("FOUND ITEM1: " + item);
//            if (item != null) {
//                System.out.println("FOUND ITEM: " + item);
//                if (PublicationItem.class.isAssignableFrom(item.getClass())) {
//                    PublicationItem publicationItem = (PublicationItem) item;
//                    System.out.println("Publication Item: " + publicationItem);
//                    OrderItem newOrderItem = new OrderItem();
//                    newOrderItem.setItem(item);
//                    newOrderItem.setItemOrderType(orderItem.getItemOrderType());
//                    newOrder.addOrderItem(newOrderItem);
////                    if()
////                    total+= publicationItem.ge
//                }
//
//            }
//
//        });

//        Order savedOrder = orderRepo.save(order);
//        System.out.println("HERE" + savedOrder.getId());

        // save rentals and purchases for reporting and records
//        savedOrder.getOrderItems().forEach(orderItem -> {
//            //Get item and update it's state
//            Item item = itemRepo.findById(orderItem.getOrderItemId()).orElse(null);
//            System.out.println("ORDER ITEM: " + orderItem);
//            System.out.println("ITEM ITEM: " + item);
//            if (item != null) {

//                orderItem.setItem(item);

//                Transaction trans = null;
//                //Or add entry to rental records
//                if (orderItem.getItemOrderType().equals(ItemOrderType.RENTAL)) {
//                    trans = new Rental();
//                }
//                // Add entry to purchase records
//                else if (orderItem.getItemOrderType().equals(ItemOrderType.PURCHASE)) {
//                    trans = new Purchase();
//                }
//                if (trans != null) {
//                    trans.setOrderItem(orderItem);
//                    trans.setOrder(order);
//                    transactionRepo.save(trans);
//                }
//            }
//        });

        Order savedOrder = orderRepo.save(modelMapper.map(order, Order.class));
//        return modelMapper.map(savedOrder, OrderDto.class);
        return new OrderDto();
    }
}
