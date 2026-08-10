package com.example.exit_exam;

import java.util.List;

public class SearchResponse {

    public int numFound;
    public List<Book> docs;

    public List<Book> getDocs() {
        return docs;
    }
}