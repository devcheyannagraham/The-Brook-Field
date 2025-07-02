package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.OrderRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepo customerRepo;

    @Test
    @org.junit.jupiter.api.Order(1)
    void newOrder() {
        Customer customer = new Customer();
        customer.setEmail("teest@test.com");

        Order order = new Order();
        order.setCustomer(customer);
        Long orderId = orderService.newOrder(order).getId();
        assertNotNull(orderRepo.findById(orderId));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void getAllOrders() {
        assertEquals(orderService.getAllOrders().size(), orderRepo.findAll().size());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void getOrder() {
        Order o = new Order();
        Long orderId = orderRepo.save(o).getId();
        assertEquals(orderId, orderService.getOrder(orderId).getId());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getCustomerOrders() {
        Order order = new Order();
        Customer c = customerRepo.save(new Customer());
        order.setCustomer(c);
        orderRepo.save(order);
        assertEquals(orderService.getCustomerOrders(c.getId()).size(),
                orderRepo.getOrdersByCustomerId(c.getId()).get().size());
    }

}