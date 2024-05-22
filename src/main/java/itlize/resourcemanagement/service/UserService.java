package itlize.resourcemanagement.service;

import itlize.resourcemanagement.entity.User;

public interface UserService {
    public boolean createUser(User user);

    public boolean verifyUser(User user);

    public boolean updatePassword(String username, String newPassword);

    public boolean deleteUser(String userName, String password);

    public String getCreationDate(String username);
}
