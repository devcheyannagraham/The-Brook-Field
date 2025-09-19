package demo.bfims.Services;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import demo.bfims.Repo.CustomerRepo;
import demo.bfims.Repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

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
        user.setSalt(generateSalt());

        // Hash password
        byte[] hashedPassword = getPasswordHash(userDto.getPassword(), user.getSalt());
        if (hashedPassword != null) {
            user.setPassword(hashedPassword);
            // Save user
            return userRepo.save(user).getUserId();
        }

        return null;
    }

    public User authenticateUser(UserDto userDto) {
        User foundUser = userRepo.findByEmail(userDto.getEmail()).orElse(null);
        if (foundUser != null) {
            byte[] hashedPassword = getPasswordHash(userDto.getPassword(), foundUser.getSalt());
            User fountUser = userRepo.findByEmailAndPassword(userDto.getEmail(), hashedPassword).orElse(null);
            if (fountUser != null) {
                return foundUser;
            }
        }
        return null;
    }

    public byte[] getPasswordHash(String password, byte[] salt) {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
            return secretKeyFactory.generateSecret(keySpec).getEncoded();

        } catch (Exception e) {
            System.out.println("Cannot create password. User not saved");
            return null;
        }
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
