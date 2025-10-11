package demo.bfims.Entities.Users;

import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Enums.UserRole;
import jakarta.persistence.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private byte[] password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToOne(cascade = CascadeType.MERGE)
    private Customer customer;
    private byte[] salt;

    public User() {
        this.userRole = UserRole.CUSTOMER;
    }

    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.email = userDto.getEmail();
        this.userRole = UserRole.CUSTOMER;
    }

    public static byte[] hashPassword(String plainTextPwd, byte[] salt) {
        KeySpec keySpec = new PBEKeySpec(plainTextPwd.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
            return secretKeyFactory.generateSecret(keySpec).getEncoded();

        } catch (Exception e) {
            System.out.println("Cannot create password. User not saved");
            return null;
        }
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", customer=" + customer +
                '}';
    }
}
