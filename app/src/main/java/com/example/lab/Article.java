package com.example.lab;

import java.io.Serializable;

public class Article implements Serializable {
    private String title;
    private String content;
    private int imgCover; // Lưu trữ ID của drawable resource, ví dụ: R.drawable.my_image
    private int viewCount;

    public Article(String title, String content, int imgCover) {
        this.title = title;
        this.content = content;
        this.imgCover = imgCover;
        this.viewCount = 0;
    }

    // Các hàm Getter và Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getImgCover() { return imgCover; }
    public void setImgCover(int imgCover) { this.imgCover = imgCover; }

    public int getViewCount() { return viewCount; }
    public void incrementViewCount() { this.viewCount++; }
}