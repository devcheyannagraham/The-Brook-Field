package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.OrderDTOs.*;
import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.Publication;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Entities.Order.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.TransactionType;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.OrderRepo;
import demo.bfims.Repo.TransactionRepo;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
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
    public OrderDto newOrder(OrderDto orderDto)
    {
        Order order = new Order();

        order.setTransactions(orderDto.getTransactions().stream()
                .map(transactionDto -> {
                    if (transactionDto.getTransactionType().equals(TransactionType.RENTAL)) {
                        return modelMapper.map(transactionDto, Rental.class);
                    } else if (transactionDto.getTransactionType().equals(TransactionType.PURCHASE)) {
                        return modelMapper.map(transactionDto, Purchase.class);
                    }
                    return null;
                }).toList());

//        // - If customer is detached (existing), it's re-attached and updated.
        Customer managedCustomer = entityManager.merge(modelMapper.map(orderDto.getCustomer(), Customer.class));
        order.setCustomer(managedCustomer);
//
        System.out.println("Order: " + order);
        return modelMapper.map(orderRepo.save(order), OrderDto.class);
    }
}
