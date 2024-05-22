package itlize.resourcemanagement.service.implementations;

import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    public boolean verifyUser(User user) {
        return ur.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    // Assume the user exists
    @Override
    public boolean updatePassword(String username, String newPassword) {
        User user = ur.findByUsername(username);
        String previousPassword = user.getPassword();

        // Check if the new password is different from the previous password
        if (!newPassword.equals(previousPassword)) {
            user.setPassword(newPassword);
            ur.save(user);
            return true;
        } else {
            // New password is the same as the previous password
            return false;
        }
    }

    @Override
    public boolean deleteUser(String userName, String password){
        if (!ur.existsByUsernameAndPassword(userName, password)) {
            return false;
        }
        ur.delete(ur.findByUsername(userName));
        return true;
    }

    @Override
    public String getCreationDate(String username) {
        User user = ur.findByUsername(username);
        Instant creationTime = user.getCreationTime();
        LocalDate localDate = creationTime.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d - yyyy");
        return STR."Member since \{formatter.format(localDate)}";
    }

}