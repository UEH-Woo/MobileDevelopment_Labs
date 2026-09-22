package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private Button btnCreateArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Gọi resource giao diện

        recyclerView = findViewById(R.id.recycler_view);
        btnCreateArticle = findViewById(R.id.btn_create_article);

        // Khởi tạo Adapter với dữ liệu tĩnh từ Repository[cite: 3]
        adapter = new ArticleAdapter(this, ArticleRepository.getInstance());
        recyclerView.setAdapter(adapter); // Gán adapter[cite: 3]
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng layout cuộn dọc[cite: 3]

        // Sự kiện chuyển sang màn hình Tạo bài viết
        btnCreateArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateArticleActivity.class); // Mở Activity mới
                startActivity(intent);
            }
        });
    }

    // Quan trọng: Hàm này giúp refresh lại danh sách khi quay lại từ màn hình Chi tiết hoặc Tạo mới (để cập nhật View count)
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}