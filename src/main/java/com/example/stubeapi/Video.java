package com.example.stubeapi;

public class Video {
    private String urlVideo;
    private String title;
    private String description;

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
        String initUrl = urlVideo.substring(0,3);
        if(!checkPattern(initUrl)){
            throw new Exception("The pattern of www. in the beginning doesn't match in this case");
        }
    }

    private boolean checkPattern(String initUrl) {
        return initUrl.equals("www.");
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
}
