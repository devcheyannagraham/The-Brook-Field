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
        if (orderDto == null) return null;
        if (orderDto.getCustomer() == null) return null;

        Order order = new Order(orderDto);

        //find customer or create new one
        if (orderDto.getCustomer().getId() == null) {
            // find by username
            User foundUser = userRepo.findByEmail(orderDto.getCustomer().getEmail()).orElse(null);
            if (foundUser != null) {
                Customer userCustomer = customerRepo.save(order.getCustomer());
                foundUser.setCustomer(userCustomer);
                userRepo.save(foundUser);
                order.setCustomer(userCustomer);
            } else {
                //Brand new customer & not a user
                Customer newCustomer = new Customer(orderDto.getCustomer());
                order.setCustomer(customerRepo.save(newCustomer));
            }


        }

        // persist item status
        List<Item> items = order.getTransactions().stream().map(Transaction::getItem).toList();
        itemRepo.saveAll(items);

        Order savedOrder = orderRepo.save(order);
        return new OrderDto(savedOrder);
    }
}
