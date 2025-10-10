package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/newadminuser")
    public ResponseEntity<String> newAdminUser(HttpServletRequest request, @RequestBody UserDto userDto, @RequestHeader("user-uuid") String uuid) {
        if (userDto == null || uuid == null || userDto.getEmail().isBlank() || userDto.getPassword().isBlank())
            return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);

        if (userService.isSessionUserAdmin(request, uuid)) {
            this.userService.saveAdminUser(userDto);
            return new ResponseEntity<>("Admin User Added", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/adminusers")
    public List<UserDto> getAdminUsers(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        if (uuid == null) return null;
        if (userService.isSessionUserAdmin(request, uuid)) {
            return userService.getAdminUsers();
        }
        return null;
    }

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<String> deleteUser(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @PathVariable("userId") Long userId) {
        if (uuid == null || userId == null) return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);
        if (userService.isSessionUserAdmin(request, uuid)) {
            if (userService.deleteUser(userId) > 0)
                return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
            else return new ResponseEntity<>("User Unavailable", HttpStatus.OK);
        }
        return null;
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
    public ResponseEntity<String> reinstateUser(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        Long userId = this.userService.getUserId(request, uuid);
        if (userId == null)
            return new ResponseEntity<>("User not signed in", HttpStatus.NOT_FOUND);

        User reinstatedUser = userService.findUserById(userId);
        if (reinstatedUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(reinstatedUser.getEmail(), HttpStatus.OK);
    }

    @GetMapping("/isadmin")
    public ResponseEntity<String> getRole(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
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
    public CustomerDto getCustomer(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        if (request.getSession(false) == null) return null;
        if (uuid == null) return null;
        Long userId = userService.getUserId(request, uuid);
        if (userId == null) return null;
        User foundUser = userService.findUserById(userId);
        if (foundUser == null) return null;
        return userService.getCustomerByUser(foundUser);
    }


}
