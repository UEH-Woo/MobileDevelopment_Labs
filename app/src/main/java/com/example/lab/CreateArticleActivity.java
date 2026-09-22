package com.example.lab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class CreateArticleActivity extends AppCompatActivity {
    private EditText etTitle, etContent;
    private RadioGroup rgImages;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        rgImages = findViewById(R.id.rg_images);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() { // Sử dụng OnClickListener như đã học[cite: 3, 4, 5]
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                // Xác định ảnh nào được chọn từ RadioGroup
                int selectedImageId = R.drawable.image1; // Ảnh mặc định

                int checkedRadioButtonId = rgImages.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.rb_image2) {
                    selectedImageId = R.drawable.image2;
                } else if (checkedRadioButtonId == R.id.rb_image3) {
                    selectedImageId = R.drawable.image3;
                }

                // Validation nhẹ
                if (!title.isEmpty() && !content.isEmpty()) {
                    // Tạo bài viết mới với ảnh được chọn
                    Article newArticle = new Article(title, content, selectedImageId);

                    // Thêm vào danh sách Repository
                    ArticleRepository.getInstance().add(newArticle);

                    // Quay lại màn hình trước đó
                    finish();
                }
            }
        });
    }
}
