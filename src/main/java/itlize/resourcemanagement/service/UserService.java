package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.User;

public interface UserService {
    public boolean createUser(User user);

    public User updatePassword(String username, String newPassword);

    public boolean deleteUser(String userName);
}
