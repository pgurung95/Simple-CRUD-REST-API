package com.pgurung1995.app.rest.Controller;

import com.pgurung1995.app.rest.Models.User;
import com.pgurung1995.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value= "/users")
    public List<User> getUsers() {
        return userRepo.findAll();

    }

    @PostMapping(value= "/save")
    public ResponseEntity<String>  saveUser(@RequestBody User user) {
        userRepo.save(user);
        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);

    }


    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {

        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setOccupation(user.getOccupation());
            updatedUser.setAge(user.getAge());
            userRepo.save(updatedUser);
            return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value= "delete/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepo.findById(id);

        if(optionalUser.isPresent()) {
            User deleteUser = optionalUser.get();
            userRepo.delete(deleteUser);
            return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
