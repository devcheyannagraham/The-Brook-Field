package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.*;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Order.*;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Repo.*;
import jakarta.persistence.EntityManager;
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
    private TransactionRepo transactionRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    private EntityManager entityManager;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream().map(OrderDto::new).toList();
    }

    public OrderDto getOrder(Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            return new OrderDto(order);
        }
        return null;
    }

    public List<OrderDto> getCustomerOrders(Long id) {
        if (id != null) {
            List<Order> results = orderRepo.findOrdersByCustomerId(1L).orElse(null);
            if (results != null && !results.isEmpty()) {
                return results.stream().map(OrderDto::new).toList();
            }
        }
        return null;
    }

    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {
        Order order = new Order(orderDto);
        Customer managedCustomer = entityManager.merge(new Customer(orderDto.getCustomer()));
        order.setCustomer(managedCustomer); // only pulls if customer has id

        // persist item status
        List<Item> items = order.getTransactions().stream().map(Transaction::getItem).toList();
        itemRepo.saveAll(items);

        Order savedOrder = orderRepo.save(order);
        System.out.println("\nORDER SAVED -" + savedOrder);
        OrderDto newOrderDto = new OrderDto(savedOrder);
        System.out.println("\nORDERDTO SAVED -" + newOrderDto);
        return newOrderDto;
    }
}
