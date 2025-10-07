package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.*;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Order.*;
import demo.bfims.Entities.Users.User;
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
    private final CustomerRepo customerRepo;
    private final UserRepo userRepo;

    public OrderService(OrderRepo orderRepo, ItemRepo itemRepo, EntityManager entityManager, CustomerRepo customerRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.entityManager = entityManager;
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {
        Order order = new Order(orderDto);
        Customer managedCustomer = null;

        Customer customer = order.getCustomer();
        if (customer == null) return null;

        // Get customer by Id
        if (customer.getId() != null) {
            managedCustomer = entityManager.merge(customer);
        } else if (customer.getEmail().isBlank()) return null;
        // Get custome by Email
        Customer foundCustomer = customerRepo.getCustomerByEmail(customer.getEmail()).orElse(null);
        if (foundCustomer != null) {
            managedCustomer = entityManager.merge(foundCustomer);
        } else {
            // Get customer by user email
            User user = this.userRepo.findByEmail(customer.getEmail()).orElse(null);
            if (user != null) {
                Customer userCustomer = new Customer(user);
                entityManager.persist(userCustomer);
                managedCustomer = entityManager.merge(userCustomer);
            }
            // brand new customer
            else {
                entityManager.persist(customer);
                managedCustomer = entityManager.merge(customer);
            }
        }
        // customer couldnt be found or created so return
        if (managedCustomer == null) return null;

        order.setCustomer(managedCustomer);

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
