package com.example.stubeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();


    public void createUser(User newUser){
        users.add(newUser);
    }

    public List<User> showAllUser() {
        return users;
    }

    public void deleteAll() {
        users.clear();
    }

    public User getUser(long uuid) throws Exception{
        for (User currentUser:users) {
            if(currentUser.getUuid() == uuid){
                return currentUser;
            }
        }
        throw new Exception("We don't have a user with that uuid");
    }
    public void deleteUser(long uuid) throws Exception{
        for (User currentUser:users) {
            if(currentUser.getUuid() == uuid){
                users.remove(currentUser);
            }
        }
        throw new Exception("We don't have a user with that uuid");
    }

    public User setUser(long uuid,User userMod) throws Exception {
        for (User currentUser:users) {
            if(currentUser.getUuid() == uuid){
                currentUser.setUserName(userMod.getUserName());
                currentUser.setEmail(userMod.getEmail());
                currentUser.setPassword(userMod.getPassword());
            }
        }
        throw new Exception("We don't have a user with that uuid");
    }

    public void addVideo(long uuid, Video newVideo) throws Exception {
        User currentUser = getUser(uuid);
        currentUser.addVideos(newVideo);
    }
    public List<Video> getVideos(long uuid)throws Exception{
        User currentUser = getUser(uuid);
        return currentUser.getVideos();
    }
}
