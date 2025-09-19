package demo.bfims.Services;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.UserRepo;
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
        User foundUser = userRepo.findByEmail(userDto.getEmail()).orElse(null);
        if (foundUser != null) {
            byte[] hashedPassword = User.hashPassword(userDto.getPassword(), foundUser.getSalt());
            User fountUser = userRepo.findByEmailAndPassword(userDto.getEmail(), hashedPassword).orElse(null);
            if (fountUser != null) {
                return foundUser;
            }
        }
        return null;
    }
}
