package demo.bfims.Services;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.PublicationRepo;
import demo.bfims.Repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    User adminUser;
    User regUser;
    UserService userService;
    UserRepo userRepo;
    CustomerRepo customerRepo;

    @Autowired
    public UserServiceTest(UserRepo userRepo, UserService userService, CustomerRepo customerRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.customerRepo = customerRepo;
        UserDto auDto = new UserDto();
        auDto.setEmail("adminuser@email.com");
        auDto.setPassword("password");
        this.userService.saveAdminUser(auDto);
        this.adminUser = this.userRepo.findByEmail(auDto.getEmail()).orElse(null);

        UserDto ruDto = new UserDto();
        ruDto.setEmail("user@email.com");
        ruDto.setPassword("password");
        this.userService.newUser(ruDto);
        this.regUser = this.userRepo.findByEmail(ruDto.getEmail()).orElse(null);
    }

    @Test
    void newUser() {
        assertNotNull(this.regUser);
        assertNull(this.userService.newUser(null));
    }

    @Test
    void saveAdminUser() {
        assertEquals(UserRole.ADMIN, this.adminUser.getUserRole());
    }

    @Test
    void getAdminUsers() {
        assertTrue(this.userService.getAdminUsers().stream().map(UserDto::getEmail).toList().contains(adminUser.getEmail()));
    }

    @Test
    void deleteUser() {
        this.userService.deleteUser(adminUser.getUserId());
        assertNull(this.userService.authenticateUser(new UserDto(adminUser)));
    }

    @Test
    void getCustomerByUser() {
        assertNull(this.userService.getCustomerByUser(regUser));
        Customer customer = new Customer();
        customer.setEmail("customer@mail.com");
        customerRepo.save(customer);
        this.regUser.setCustomer(customer);
        this.userRepo.save(this.regUser);
        assertEquals(this.userService.getCustomerByUser(regUser).getId(), customer.getId());
    }


    @Test
    void authenticateUser() {
        UserDto uDto = new UserDto(this.regUser);
        uDto.setPassword("password");
        assertNotNull(this.userService.authenticateUser(uDto));
        assertNull(this.userService.authenticateUser(null));
    }

    @Test
    void findUserById() {
        assertEquals(this.userService.findUserById(this.regUser.getUserId()).getUserId(), this.regUser.getUserId());
        assertNull(this.userService.findUserById(null));
    }

}