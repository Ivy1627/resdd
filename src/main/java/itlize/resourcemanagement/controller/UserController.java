package itlize.resourcemanagement.controller;

import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;

    public static class UpdatePasswordRequest {
        private User user;
        private String newPassword;

        public User getUser() {
            return user;
        }

        public String getPassword() {
            return newPassword;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        boolean success = us.verifyUser(user);
        if(success){
            return new ResponseEntity<String>("login successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Username or password incorrect", HttpStatus.CONFLICT);
    }

    @PostMapping("/create")
    public ResponseEntity<String> add(@RequestBody User user){
        boolean success = us.createUser(user);
        if(success){
            return new ResponseEntity<String>("new user created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("The username is duplicate", HttpStatus.CONFLICT);
    }

    @GetMapping("/memberSince/{username}")
    public ResponseEntity<String> getCreationTime(@PathVariable String username){
        return new ResponseEntity<String>(us.getCreationDate(username), HttpStatus.OK);
    }

    @PatchMapping("/updatePassword/{username}")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody String newPassword) {
        boolean success = us.updatePassword(username, newPassword);
        if(success){
            return new ResponseEntity<String>("Password updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Please create a new password", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        boolean success = us.deleteUser(user.getUsername(), user.getPassword());
        if(success){
            return new ResponseEntity<String>("Account successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username or password incorrect", HttpStatus.CONFLICT);
    }
}
