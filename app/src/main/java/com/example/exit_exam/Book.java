package com.example.exit_exam;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    public String title;
    public List<String> author_name;
    public Integer first_publish_year;
    public Integer cover_i;
    public List<String> isbn;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public Integer getFirst_publish_year() {
        return first_publish_year;
    }

    public Integer getCover_i() {
        return cover_i;
    }

    public List<String> getIsbn() {
        return isbn;
    }
}