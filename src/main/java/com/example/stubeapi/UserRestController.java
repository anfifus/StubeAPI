package com.example.stubeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
public class UserRestController {

    private UserService serviceUser;
    @Autowired
    public UserRestController(UserService serviceUser) {
        this.serviceUser = serviceUser;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        serviceUser.createUser(user);
    }
    @GetMapping("/users")
    public List<User> showUsers(){
        return serviceUser.showAllUser();
    }
    @DeleteMapping("/users")
    public void deleteAll(){
        serviceUser.deleteAll();
    }
    @GetMapping("/users/{uuid}")
    public User getUserById(@PathVariable long uuid) throws Exception {
        return serviceUser.getUser(uuid);
    }
    @DeleteMapping("/users/{uuid}")
    public void deleteUserById(@PathVariable long uuid) throws Exception {
         serviceUser.deleteUser(uuid);
    }
    @PutMapping("/users/{uuid}")
    public User setUserById(@PathVariable long uuid,@RequestBody User userMod) throws Exception {
        return serviceUser.setUser(uuid,userMod);
    }
    @PostMapping("/users/{uuid}/videos")
    public void createVideo(@PathVariable long uuid,@RequestBody Video newVideo) throws Exception {
        serviceUser.addVideo(uuid,newVideo);
    }
    @GetMapping("/users/{uuid}/videos")
    public void createVideo(@PathVariable long uuid) throws Exception {
        serviceUser.getVideos(uuid);
    }
}
