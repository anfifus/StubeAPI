package com.example.stubeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/users/{uuidUser}")
    public User getUserById(@PathVariable long uuidUser) throws Exception {
        return serviceUser.getUser(uuidUser);
    }
    @DeleteMapping("/users/{uuidUser}")
    public void deleteUserById(@PathVariable long uuidUser){
         serviceUser.deleteUser(uuidUser);
    }
    @PutMapping("/users/{uuidUser}")
    public User setUserById(@PathVariable long uuidUser,@RequestBody User userMod) throws Exception {
        return serviceUser.setUser(uuidUser,userMod);
    }
    @PostMapping("/users/{uuidUser}/videos")
    public void createVideo(@PathVariable long uuidUser,@RequestBody Video newVideo) throws Exception {
        serviceUser.addVideo(uuidUser, newVideo);
    }
    @GetMapping("/users/{uuidUser}/videos")
    public List<Video> getVideos(@PathVariable long uuidUser) throws Exception {
        return serviceUser.getVideos(uuidUser);
    }
    @GetMapping("users/{uuidUser}/videos/{uuidVideo}")
    public Video getVideo(@PathVariable long uuidUser, @PathVariable long uuidVideo) throws Exception {
        return serviceUser.getVideo(uuidUser,uuidVideo);
    }
    @PostMapping("users/{uuidUser}/videos/{uuidVideo}")
    public void createScore(@PathVariable long uuidUser,@PathVariable long uuidVideo,@RequestBody Score newScore) throws Exception {
        serviceUser.addScore(uuidUser,uuidVideo,newScore);
    }
}
