package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.*;
import demo.bfims.Entities.Order.*;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.OrderRepo;
import demo.bfims.Repo.TransactionRepo;
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
        System.out.println("\nORDER NEW -" + orderDto);
        Order order = new Order();
        orderDto.getTransactions().forEach(transactionDto -> {
            System.out.println("\nTRANSACTIONDTO -" + transactionDto);
            Transaction transactionEntity = Transaction.mapToTransactionSubclass(transactionDto);
            System.out.println("\nTRANSACTIONENTITY -" + transactionEntity);
        });


//        order.setTransactions(orderDto.getTransactions().stream()
//                .map(transactionDto -> {
//                    if (transactionDto.getTransactionType().equals(TransactionType.RENTAL)) {
//                        return modelMapper.map(transactionDto, Rental.class);
//                    } else if (transactionDto.getTransactionType().equals(TransactionType.PURCHASE)) {
//                        return modelMapper.map(transactionDto, Purchase.class);
//                    }
//                    return null;
//                }).toList());
//
//        // - If customer is detached (existing), it's re-attached and updated.
//        Customer customer = modelMapper.map(orderDto.getCustomer(), Customer.class);
//        if (customer.getId() == null && customer.getEmail() != null) {
//            Customer foundCustomer = customerRepo.getCustomerByEmail(customer.getEmail()).orElse(null);
//            if (foundCustomer != null) {
//                customer = foundCustomer;
//            }
//        }
//        Customer managedCustomer = entityManager.merge(customer);
//        order.setCustomer(managedCustomer);
//        return modelMapper.map(orderRepo.save(order), OrderDto.class);

        return null;
    }
}
