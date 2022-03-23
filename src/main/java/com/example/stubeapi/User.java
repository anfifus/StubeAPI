package com.example.stubeapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {
    @Id
    private long uuidUser = UUID.randomUUID().getMostSignificantBits();
    private String userName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Video> videos = new ArrayList<>();


    public User(){}

    public User(String userName, String email, String password) throws Exception{
        checkPass(password);
        checkEmail(email);
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    private void checkPass(String password) throws Exception {
        if(checkPattern(password)) throw new Exception("The pattern of the pass must have a number and be a length of 7 or more");
    }

    private boolean checkPattern(String password) {
        return (password.length()<7 || !password.matches(".*\\d.*"));
    }
    private Pattern ValidEmailRegex(){
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);

    }
    private void checkEmail(String email) throws Exception{
        Matcher match = ValidEmailRegex().matcher(email);
        if(!match.find()) throw new Exception("The email doesn't match with the parameters of a email");
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getUuidUser() {
        return uuidUser;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) throws Exception {
        checkEmail(email);
        this.email = email;
    }

    public void setPassword(String password) throws Exception {
        checkPass(password);
        this.password = password;
    }
    public void addVideos(Video video,User user){
        video.addUser(user);
        videos.add(video);
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
