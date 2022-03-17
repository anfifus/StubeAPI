package com.example.stubeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.beans.BeanProperty;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private long uuid = UUID.randomUUID().getMostSignificantBits();
    private String userName;
    private String email;
    private String password;
    private List<Video> videos = new ArrayList<>();


    public User(){}

    public User(String userName, String email, String password) throws Exception{
        checkPass(password);
        checkEmail(email);
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

//ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"The pattern of the pass must have a number and be a length of 7 or more")
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

    public long getUuid() {
        return uuid;
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
    public void addVideos(Video video) throws Exception {
         videos.add(video);
    }

}
