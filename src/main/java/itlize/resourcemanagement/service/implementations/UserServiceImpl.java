package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo ur;

    @Override
    public boolean createUser(User user) {
        if (ur.existsByUsername(user.getUsername())) {
            return false;
        }
        ur.save(user);
        return true;
    }

    @Override
    public User updatePassword(String username, String newPassword) {
        User user = ur.findByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            ur.save(user);
        }
        // Return the updated user (or null if user does not exist)
        return user;
    }

    @Override
    public boolean deleteUser(String userName){
        if (!ur.existsByUsername(userName)) {
            return false;
        }
        ur.delete(ur.findByUsername(userName));
        return true;
    }

}
