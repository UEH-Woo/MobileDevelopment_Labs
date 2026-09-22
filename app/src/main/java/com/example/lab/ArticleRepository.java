package com.example.lab;

import java.util.ArrayList;

public class ArticleRepository {
    // Biến tĩnh lưu trữ danh sách các bài viết trong bộ nhớ
    private static ArrayList<Article> articleList;

    public static ArrayList<Article> getInstance() {
        if (articleList == null) {
            articleList = new ArrayList<>();
            // Tạo sẵn 3 bài viết
            articleList.add(new Article("Bài viết số 1", "Đây là nội dung của bài viết thứ nhất, xin chào các bạn.", R.drawable.image1));
            articleList.add(new Article("Bài viết số 2", "Bài viết này có nội dung hơi dài hơn một chút để test xem sao.", R.drawable.image2));
            articleList.add(new Article("Bài viết số 3", "Chào buổi sáng mọi người, chúc một ngày làm việc tốt lành.", R.drawable.image3));
            // Hãy đảm bảo bạn đã copy file image1, image2, image3 vào thư mục res/drawable
        }
        return articleList;
    }
}
