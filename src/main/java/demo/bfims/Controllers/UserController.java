package demo.bfims.Controllers;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Repo.UserRepo;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http//localhost:4200")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity<String> newUser(HttpSession session, @RequestBody UserDto userDto) {
        Long userId = userService.newUser(userDto);
        if (userId == null) return new ResponseEntity<>("Unable To Create User", HttpStatus.OK);

        String sessionId = session.getId();
        session.setAttribute(userId.toString(), sessionId);
        return new ResponseEntity<>(userId.toString(), HttpStatus.OK);
    }

    @PostMapping("/authenticateuser")
    public ResponseEntity<String> authenticateUser(HttpSession session, @RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getPassword() == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.OK);

        User foundUser = userService.authenticateUser(userDto);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.OK);

        String sessionId = session.getId();
        session.setAttribute(foundUser.getUserId().toString(), sessionId);
        return new ResponseEntity<>(foundUser.getUserId().toString(), HttpStatus.OK);
    }

    @PostMapping("/isAdmin")
    public ResponseEntity<String> getRole(HttpSession session, @RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getPassword() == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.OK);

        User foundUser = userService.authenticateUser(userDto);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.OK);
        String sessionId = session.getAttribute(foundUser.getUserId().toString()).toString();
        if (sessionId.isEmpty() || sessionId.equals("null") || !session.getId().equals(sessionId))
            return new ResponseEntity<>("Invalid Session", HttpStatus.OK);

        if (foundUser.getUserRole().equals(UserRole.ADMIN))
            return new ResponseEntity<>("Admin", HttpStatus.OK);
        else return new ResponseEntity<>("Customer", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout", HttpStatus.OK);
    }


}
