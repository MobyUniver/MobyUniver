package com.gh.MobyUniver;


import android.graphics.Bitmap;

public class KursesItems {
    String id, kurs, video;
    Bitmap img;


    public KursesItems(String id, String kurs, Bitmap img, String video) {
        this.id = id;
        this.kurs = kurs;
        this.img = img;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public String getKurs() {
        return kurs;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getVideo() {
        return video;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKurs(String kurs) {
        this.kurs = kurs;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
