package demo.bfims.Services;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final CustomerRepo customerRepo;


    public UserService(UserRepo userRep, CustomerRepo customerRepo) {
        this.userRepo = userRep;
        this.customerRepo = customerRepo;
    }

    //add user
    public Long newUser(UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getEmail() == null) {
            return null;
        }
        User user = new User(userDto);

        if (userRepo.findByEmail(userDto.getEmail()).orElse(null) != null) {
            // user already exists
            System.out.println(userDto.getEmail() + " already exists");
            return null;
        }

        // Connect customer account if exists;
        customerRepo.getCustomerByEmail(userDto.getEmail()).ifPresent(user::setCustomer);

        // Generate salt
        user.setSalt(User.generateSalt());
        // Hash password
        user.setPassword(User.hashPassword(userDto.getPassword(), user.getSalt()));

        // Error hashing password. Abort.
        if (user.getPassword() == null) return null;

        // Save user
        return userRepo.save(user).getUserId();


    }

    public User authenticateUser(UserDto userDto) {
        if(userDto.getEmail() == null || userDto.getPassword() == null) return null;
        User foundUser = userRepo.findByEmail(userDto.getEmail()).orElse(null);
        if (foundUser != null) {
            byte[] hashedPassword = User.hashPassword(userDto.getPassword(), foundUser.getSalt());
            User user = userRepo.findByEmailAndPassword(userDto.getEmail(), hashedPassword).orElse(null);
            if (user != null) {
                return foundUser;
            }
        }
        return null;
    }

    public User findUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public Long getUserId(HttpServletRequest request, String userUid) {
        if (userUid == null || request == null) return null;

        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Long) session.getAttribute(userUid);
        }
        return null;
    }

    public boolean isSessionUserAdmin(HttpServletRequest request, String uuid) {
        if (uuid == null || request == null) return false;

        Long userId = this.getUserId(request, uuid);
        if (userId == null) return false;

        User foundUser = this.findUserById(userId);
        if (foundUser == null) return false;

        return foundUser.getUserRole().equals(UserRole.ADMIN);
    }
}
