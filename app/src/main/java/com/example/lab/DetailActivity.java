package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvContent, tvViewCount;
    private ImageView imgCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); //[cite: 6]

        tvTitle = findViewById(R.id.detail_tv_title);
        tvContent = findViewById(R.id.detail_tv_content);
        tvViewCount = findViewById(R.id.detail_tv_view_count);
        imgCover = findViewById(R.id.detail_img_cover);

        // Nhận dữ liệu từ Intent[cite: 4]
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ARTICLE_POSITION")) {
            int position = intent.getIntExtra("ARTICLE_POSITION", 0);

            // Lấy Article hiện tại từ Repository tĩnh
            Article currentArticle = ArticleRepository.getInstance().get(position);

            // Đổ dữ liệu lên Views
            tvTitle.setText(currentArticle.getTitle());
            tvContent.setText(currentArticle.getContent());
            tvViewCount.setText("Lượt xem: " + currentArticle.getViewCount());
            imgCover.setImageResource(currentArticle.getImgCover()); //[cite: 6]
        }
    }
}
