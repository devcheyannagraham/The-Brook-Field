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
        request.getSession(true);
        System.out.println("SessionID in New User: " + request.getSession(false).getId());
        return new ResponseEntity<>(userId.toString(), HttpStatus.OK);
    }

    @PostMapping("/authenticateuser")
    public ResponseEntity<String> authenticateUser(HttpServletRequest request, @RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getPassword() == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.OK);

        User foundUser = userService.authenticateUser(userDto);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.OK);

        request.getSession(true);
        System.out.println("SessionID in Authenticate User: " + request.getSession(false).getId());

        return new ResponseEntity<>(foundUser.getUserId().toString(), HttpStatus.OK);
    }

    @PostMapping("/isadmin")
    public ResponseEntity<String> getRole(HttpServletRequest request, @RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getPassword() == null)
            return new ResponseEntity<>("Missing Credentials", HttpStatus.OK);

        User foundUser = userService.authenticateUser(userDto);
        if (foundUser == null)
            return new ResponseEntity<>("User does not exist", HttpStatus.OK);

        HttpSession session = request.getSession(false);
        if (session == null)
            return new ResponseEntity<>("Invalid Session", HttpStatus.OK);


        System.out.println("SessionID in getrole " + request.getSession(false).getId());

        if (foundUser.getUserRole().equals(UserRole.ADMIN))
            return new ResponseEntity<>(UserRole.ADMIN.toString(), HttpStatus.OK);

        else return new ResponseEntity<>(UserRole.CUSTOMER.toString(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout", HttpStatus.OK);
    }


}
