package com.example.stubeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private  UserRepository userRepository;
    private VideoRepository videoRepository;
    private ScoreRepository scoreRepository;

    @Autowired
    public UserService(UserRepository userRepository, VideoRepository videoRepository, ScoreRepository scoreRepository){
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.scoreRepository = scoreRepository;
    }

    public void createUser(User newUser){
        userRepository.save(newUser);
    }

    public List<User> showAllUser() {
        List<User> showUsers = new ArrayList<>();
        userRepository.findAll().forEach(showUsers::add);
        return showUsers;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User getUser(long uuid) throws Exception{
        Optional<User> userWithID = userRepository.findById(uuid);
            if(userWithID.isPresent()){
                return userWithID.get();
            }
        throw new Exception("We don't have a user with that uuid");
    }
    public void deleteUser(long uuid){
        userRepository.deleteById(uuid);
    }

    public User setUser(long uuid,User userMod) throws Exception {
        User userToUpdate = getUser(uuid);

        userToUpdate.setUserName(userMod.getUserName());
        userToUpdate.setEmail(userMod.getEmail());
        userToUpdate.setPassword(userMod.getPassword());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public void addVideo(long uuid, Video newVideo) throws Exception {
        User currentUser = getUser(uuid);
        currentUser.addVideos(newVideo,currentUser);
        videoRepository.saveAll(currentUser.getVideos());

    }
    public List<Video> getVideos(long uuid)throws Exception{
        User currentUser = getUser(uuid);
        if(currentUser != null){
            return currentUser.getVideos();
        }
        throw new Exception("The user we get doesn't exist");
        //return videoRepository.findAllByUser(currentUser);
    }

    public Video getVideo(long uuidUser, long uuidVideo) throws Exception{
        User currentUser = getUser(uuidUser);

        for (Video currentVideo : currentUser.getVideos()) {
            if(currentVideo.getUuidVideo() == uuidVideo){
                return currentVideo;
            }
        }
        throw new Exception("We don't have a video with that uuid");
    }


    public void addScore(long uuidUser, long uuidVideo, Score newScore) throws Exception {
        Video currentVideo = getVideo(uuidUser,uuidVideo);
        currentVideo.addScores(newScore,currentVideo);
        scoreRepository.saveAll(currentVideo.getScores());
    }
}
