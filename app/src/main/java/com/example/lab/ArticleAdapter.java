package com.example.lab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private Context context;
    private ArrayList<Article> articleList;

    public ArticleAdapter(Context context, ArrayList<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentArticle = articleList.get(position);
        holder.tvTitle.setText(currentArticle.getTitle());
        holder.tvContent.setText(currentArticle.getContent());
        holder.tvViewCount.setText("Views: " + currentArticle.getViewCount());
        holder.imgCover.setImageResource(currentArticle.getImgCover()); // Load ảnh local

        // Sự kiện click vào bài viết
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tăng view count lên 1
                currentArticle.incrementViewCount();

                // Mở màn hình DetailActivity, truyền index (position) qua Intent
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ARTICLE_POSITION", position); // Truyền vị trí của item trong list
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvContent, tvViewCount;
        public ImageView imgCover;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvViewCount = itemView.findViewById(R.id.tv_view_count);
            imgCover = itemView.findViewById(R.id.img_cover);
        }
    }
}
