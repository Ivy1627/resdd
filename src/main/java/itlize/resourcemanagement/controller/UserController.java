package itlize.resourcemanagement.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import itlize.resourcemanagement.entity.Project;
import itlize.resourcemanagement.entity.User;
import itlize.resourcemanagement.repository.UserRepo;
import itlize.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;
    @Autowired
    private UserRepo ur;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<UserDetailsDTO> login(@RequestBody User user){
        boolean success = us.verifyUser(user);
        if (success) {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            User authenticatedUser = ur.findByUsername(user.getUsername());
            userDetails.setUsername(authenticatedUser.getUsername());
            userDetails.setMemberSince(authenticatedUser.getCreationTime());
            userDetails.setId(authenticatedUser.getId());
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<String> add(@RequestBody User user){
        boolean success = us.createUser(user);
        if(success){
            return new ResponseEntity<String>("new user created", HttpStatus.OK);
        }
        return new ResponseEntity<String>("The username is duplicate", HttpStatus.CONFLICT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/memberSince/{username}")
    public ResponseEntity<String> getCreationTime(@PathVariable String username){
        return new ResponseEntity<String>(us.getCreationDate(username), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/updatePassword/{username}")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody String newPassword) {
        boolean success = us.updatePassword(username, newPassword);
        if(success){
            return new ResponseEntity<String>("Password updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Please create a new password", HttpStatus.CONFLICT);
    }

    public static class UserDetailsDTO {
        private Long id;
        private String username;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM-yyyy")
        private String memberSince;

        public UserDetailsDTO() {
        }

        public UserDetailsDTO(String username, Long id, Instant creationTime) {
            this.id = id;
            this.username = username;
            setMemberSince(creationTime);
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMemberSince() {
            return memberSince;
        }

        public void setMemberSince(Instant creationTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy")
                    .withZone(ZoneId.systemDefault());
            this.memberSince = formatter.format(creationTime);
        }
        public Long getId(){
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/details/{username}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable String username){
        User user = ur.findByUsername(username);
        if (user != null) {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setUsername(user.getUsername());
            userDetails.setMemberSince(user.getCreationTime());
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        boolean success = us.deleteUser(user.getUsername(), user.getPassword());
        if(success){
            return new ResponseEntity<String>("Account successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username or password incorrect", HttpStatus.CONFLICT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = ur.findById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
