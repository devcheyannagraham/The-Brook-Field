package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.*;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Order.*;
import demo.bfims.Repo.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;
    private final EntityManager entityManager;

    public OrderService(OrderRepo orderRepo, ItemRepo itemRepo, EntityManager entityManager) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.entityManager = entityManager;
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
