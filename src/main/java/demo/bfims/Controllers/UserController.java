package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity<String> newUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        Long userId = userService.newUser(userDto);
        if (userId == null) return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);
        UUID uuid = UUID.randomUUID();
        HttpSession session = request.getSession(true);
        session.setAttribute(uuid.toString(), userId);
        return new ResponseEntity<>(uuid.toString(), HttpStatus.OK);
    }

    @PostMapping("/authenticateuser")
    public ResponseEntity<String> authenticateUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getPassword() == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.BAD_REQUEST);

        User foundUser = userService.authenticateUser(userDto);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);

        UUID uuid = UUID.randomUUID();
        HttpSession session = request.getSession(true);
        session.setAttribute(uuid.toString(), foundUser.getUserId());
        return new ResponseEntity<>(uuid.toString(), HttpStatus.OK);
    }

    @PostMapping("/reinstateuser")
    public ResponseEntity<String> reinstateUser(HttpServletRequest request, @RequestBody String uuid) {
        Long userId = this.userService.getUserId(request, uuid);
        if (userId == null)
            return new ResponseEntity<>("User not signed in", HttpStatus.NOT_FOUND);

        User reinstatedUser = userService.findUserById(userId);
        if (reinstatedUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(reinstatedUser.getEmail(), HttpStatus.OK);
    }

    @PostMapping("/isadmin")
    public ResponseEntity<String> getRole(HttpServletRequest request, @RequestBody String uuid) {
        if (request == null || uuid == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.BAD_REQUEST);

        if (this.userService.isSessionUserAdmin(request, uuid))
            return new ResponseEntity<>(UserRole.ADMIN.toString(), HttpStatus.OK);
        return new ResponseEntity<>(UserRole.CUSTOMER.toString(), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        if (session != null)
            session.invalidate();
        return new ResponseEntity<>("User logged out", HttpStatus.OK);
    }

    @PostMapping("/getcustomer")
    public CustomerDto getCustomer(HttpServletRequest request, @RequestBody String uuid) {
        if(request.getSession(false) == null) return null;
        if(uuid == null) return null;
        Long userId = userService.getUserId(request, uuid);
        if(userId == null) return null;
        User foundUser = userService.findUserById(userId);
        if(foundUser == null) return null;
        return userService.getCustomerByUser(foundUser);
    }


}
