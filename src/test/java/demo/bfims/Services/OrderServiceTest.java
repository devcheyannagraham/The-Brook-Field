package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Repo.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepo customerRepo;

    @Test
    void newOrder() {
        assertNull(this.orderService.newOrder(null));
        OrderDto order = new OrderDto();
        assertNull(this.orderService.newOrder(order));

        CustomerDto customer = new CustomerDto();
        customer.setEmail("test@test.com");
        order.setCustomer(customer);
        assertNotNull(orderService.newOrder(order));
    }

}