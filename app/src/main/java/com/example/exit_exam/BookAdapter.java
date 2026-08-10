package com.example.exit_exam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter
        extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    Context context;

    List<Book> books = new ArrayList<>();

    public BookAdapter(Context context) {
        this.context = context;
    }

    public void setBooks(List<Book> books) {

        this.books.clear();

        if (books != null) {
            this.books.addAll(books);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(
                        R.layout.book_item,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Book book = books.get(position);

        holder.title.setText(
                book.getTitle()
        );

        String author = "Unknown Author";

        if (book.getAuthor_name() != null
                && !book.getAuthor_name().isEmpty()) {

            author = book.getAuthor_name().get(0);
        }

        holder.author.setText(author);

        if (book.getCover_i() != null) {

            String url =
                    "https://covers.openlibrary.org/b/id/"
                            + book.getCover_i()
                            + "-M.jpg";

            Glide.with(context)
                    .load(url)
                    .into(holder.cover);

        } else {

            holder.cover.setImageResource(
                    android.R.drawable.ic_menu_gallery
            );
        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            context,
                            ActivityDetail.class
                    );

            intent.putExtra("book", book);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title;
        TextView author;

        public ViewHolder(
                @NonNull View itemView) {

            super(itemView);

            cover =
                    itemView.findViewById(
                            R.id.imgCover
                    );

            title =
                    itemView.findViewById(
                            R.id.txtTitle
                    );

            author =
                    itemView.findViewById(
                            R.id.txtAuthor
                    );
        }
    }
}
