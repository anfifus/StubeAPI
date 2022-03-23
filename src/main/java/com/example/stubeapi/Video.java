package com.example.stubeapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Video {
    private long uuidVideo = UUID.randomUUID().getMostSignificantBits();
    private String urlVideo;
    private String title;
    private String description;
    private List<Score> scores = new ArrayList<>();
    public Video() {
    }

    public Video(String urlVideo, String title, String description) throws Exception {
        checkUrlVideo(urlVideo);
        checkTitle(title);
        this.urlVideo = urlVideo;
        this.title = title;
        this.description = description;
    }

    private void checkTitle(String title) throws Exception{
        if(title.length()<10){
            throw new Exception("The title must be a length of 10 or more");
        }
    }

    private void checkUrlVideo(String urlVideo) throws Exception{
        String initUrl = urlVideo.substring(0,4);
        if(!checkPattern(initUrl)){
            throw new Exception("The pattern of www. in the beginning doesn't match in this case");
        }
    }

    private boolean checkPattern(String initUrl) {
        return initUrl.equals("www.");
    }

    public long getUuidVideo() {
        return uuidVideo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setUrlVideo(String urlVideo) throws Exception {
        checkUrlVideo(urlVideo);
        this.urlVideo = urlVideo;
    }

    public void setTitle(String title) throws Exception {
        checkTitle(title);
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addScores(Score score) {
        this.scores.add(score);
    }

}
