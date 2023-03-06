package com.example.androidtest.listview;

import java.text.DecimalFormat;

public class Result {
    private String name;
    private String score;
    private int imageId;

    public Result(String name, double score, int imageId) {
        DecimalFormat df = new DecimalFormat("0.00%");
        this.name=name;
        this.score="可能性：   "+df.format(score);
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public int getImageId() {
        return imageId;
    }

}
