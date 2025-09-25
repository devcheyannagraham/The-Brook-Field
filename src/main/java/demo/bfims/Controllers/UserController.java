package demo.bfims.Controllers;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity<String> newUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        Long userId = userService.newUser(userDto);
        if (userId == null) return new ResponseEntity<>("Username Unavailable", HttpStatus.OK);
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
        HttpSession session = request.getSession(false);
        if (session == null)
            return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);

        Long userId = (Long) session.getAttribute(uuid);
        if (userId == null)
            return new ResponseEntity<>("User not signed in", HttpStatus.NOT_FOUND);

        User reinstatedUser = userService.reinstateUserById(userId);
        if (reinstatedUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(reinstatedUser.getEmail(), HttpStatus.OK);
    }

    @PostMapping("/isadmin")
    public ResponseEntity<String> getRole(HttpServletRequest request, @RequestBody String uuid) {
        if (uuid == null)
            return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);

        HttpSession session = request.getSession(false);
        if (session == null)
            return new ResponseEntity<>("Invalid Session", HttpStatus.NOT_FOUND);

        Long userUid = (Long) session.getAttribute(uuid);
        if (userUid == null)
            return new ResponseEntity<>("Username Unavailable", HttpStatus.NOT_FOUND);

        User foundUser = userService.reinstateUserById(userUid);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);


        if (foundUser.getUserRole().equals(UserRole.ADMIN))
            return new ResponseEntity<>(UserRole.ADMIN.toString(), HttpStatus.OK);
        else return new ResponseEntity<>(UserRole.CUSTOMER.toString(), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        if (session != null)
            session.invalidate();
        return new ResponseEntity<>("User logged out", HttpStatus.OK);
    }
}
