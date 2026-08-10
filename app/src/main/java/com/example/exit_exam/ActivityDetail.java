package com.example.exit_exam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_details);

        setTitle("Book Details");

        ImageView cover =
                findViewById(R.id.imgCover);

        TextView title =
                findViewById(R.id.txtTitle);

        TextView author =
                findViewById(R.id.txtAuthor);

         TextView year =
                findViewById(R.id.txtYear);

        TextView isbn =
                findViewById(R.id.txtIsbn);

        Book book =
                (Book) getIntent()
                        .getSerializableExtra("book");

        if (book == null) {
            finish();
            return;
        }

        title.setText(
                "Title: " + book.getTitle()
        );

        String authorName = "Unknown";

        if (book.getAuthor_name() != null
                && !book.getAuthor_name().isEmpty()) {

            authorName =
                    android.text.TextUtils.join(
                            ", ",
                            book.getAuthor_name()
                    );
        }

        author.setText(
                "Author: " + authorName
        );

        String publishYear = "Unknown";

        if (book.getFirst_publish_year() != null) {

            publishYear =
                    String.valueOf(
                            book.getFirst_publish_year()
                    );
        }

        year.setText(
                "Publish Year: " + publishYear
        );

        String isbnNumber = "Not Available";

        if (book.getIsbn() != null
                && !book.getIsbn().isEmpty()) {

            isbnNumber =
                    book.getIsbn().get(0);
        }

        isbn.setText(
                "ISBN: " + isbnNumber
        );

        if (book.getCover_i() != null) {

            String url =
                    "https://covers.openlibrary.org/b/id/"
                            + book.getCover_i()
                            + "-L.jpg";

            Glide.with(this)
                    .load(url)
                    .into(cover);
        }
    }
}