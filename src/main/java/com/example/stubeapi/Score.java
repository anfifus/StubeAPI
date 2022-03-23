package com.example.stubeapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "scores")
public class Score {
    @Id
    private Long id = UUID.randomUUID().getMostSignificantBits();
    private int evaluation;
    private String comment;
    @ManyToOne
    private Video video;
    public Score() {
    }

    public Score(int evaluation, String comment) throws Exception {
        checkEvaluation(evaluation);
        this.evaluation = evaluation;
        this.comment = comment;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) throws Exception{
        checkEvaluation(evaluation);
        this.evaluation = evaluation;
    }

    private void checkEvaluation(int evaluation) throws Exception{
        if(!evaluateScore(evaluation)) throw new Exception("Your evaluation is bad must be between 1 and 5");
    }

    private boolean evaluateScore(int evaluation) {
        return evaluation >= 1 && evaluation <= 5;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addVideo(Video video) {
        this.video = video;
    }
}
