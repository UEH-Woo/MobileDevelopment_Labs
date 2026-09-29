package com.example.lab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtEmail, edtTel;
    private Button btnSpSave, btnSpLoad, btnSqlSave, btnSqlLoad;
    private DatabaseHelper dbHelper;

    private static final String PREF_NAME = "StudentPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtTel = findViewById(R.id.edt_tel);

        btnSpSave = findViewById(R.id.btn_sp_save);
        btnSpLoad = findViewById(R.id.btn_sp_load);
        btnSqlSave = findViewById(R.id.btn_sql_save);
        btnSqlLoad = findViewById(R.id.btn_sql_load);

        dbHelper = new DatabaseHelper(this);

        // 1. Lưu bằng SharedPreferences
        btnSpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID trước khi Save", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Ghép ID vào trước key để tạo ra các key độc nhất cho từng người
                editor.putString(id + "_name", edtName.getText().toString());
                editor.putString(id + "_email", edtEmail.getText().toString());
                editor.putString(id + "_tel", edtTel.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this, "Saved ID " + id + " to SharedPreferences", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. Load bằng SharedPreferences (Đã fix logic load theo ID)
        btnSpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID để Load từ SP", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

                // Kiểm tra xem ID được lưu tên chưa (dùng giá trị mặc định là null)
                String savedName = sharedPreferences.getString(id + "_name", null);

                if (savedName != null) {
                    // Nếu tìm thấy, tiến hành gắn dữ liệu lên các ô EditText
                    edtName.setText(savedName);
                    edtEmail.setText(sharedPreferences.getString(id + "_email", ""));
                    edtTel.setText(sharedPreferences.getString(id + "_tel", ""));
                    Toast.makeText(MainActivity.this, "Loaded ID " + id + " from SP", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy dữ liệu của ID " + id + " trong SP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 3. Lưu bằng SQLite
        btnSqlSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtTel.getText().toString()
                );
                dbHelper.saveStudent(student);
                Toast.makeText(MainActivity.this, "Saved to SQLite", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Load bằng SQLite (Tìm theo ID)
        btnSqlLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID để Load từ SQLite", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = dbHelper.getStudent(id);
                if (student != null) {
                    edtName.setText(student.getName());
                    edtEmail.setText(student.getEmail());
                    edtTel.setText(student.getTel());
                    Toast.makeText(MainActivity.this, "Loaded from SQLite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy Student với ID này", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}