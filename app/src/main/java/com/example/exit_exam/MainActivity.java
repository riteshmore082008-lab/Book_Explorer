package com.example.exit_exam;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter adapter;
    SearchView searchView;

    String fields =
            "title,author_name,first_publish_year,cover_i,isbn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle("Book Explorer");

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        searchBooks("android");

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        if (!query.trim().isEmpty()) {
                            searchBooks(query.trim());
                        }

                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        if (newText.trim().isEmpty()) {
                            searchBooks("android");
                        }

                        return true;
                    }
                }
        );


        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        adapter = new BookAdapter(this);

        recyclerView.setAdapter(adapter);


    }

    private void searchBooks(String query) {

        ApiService apiService =
                ApiClient.getApiService();

        Call<SearchResponse> call =
                apiService.searchBooks(
                        query,
                        fields
                );

        call.enqueue(new Callback<SearchResponse>() {

            @Override
            public void onResponse(
                    Call<SearchResponse> call,
                    Response<SearchResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    SearchResponse result =
                            response.body();

                    adapter.setBooks(
                            result.getDocs()
                    );

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "No results found",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(
                    Call<SearchResponse> call,
                    Throwable t) {

                Toast.makeText(
                        MainActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}