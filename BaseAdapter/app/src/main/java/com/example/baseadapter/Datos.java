package com.example.baseadapter;

public class Datos {
    private int imageResId;
    private String title;
    private String content;

    public Datos(int i, String s1, String s2){
        this.imageResId = i;
        this.title = s1;
        this.content = s2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
