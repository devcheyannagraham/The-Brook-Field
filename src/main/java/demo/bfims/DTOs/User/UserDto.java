package demo.bfims.DTOs.User;

import demo.bfims.Entities.Users.User;

public class UserDto {
    private Long userId;
    private String email;
    private String password;


    public UserDto() {
    }

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
