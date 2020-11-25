package com.example.quizzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.quizzer.QuestionActivity.FILE_NAME;
import static com.example.quizzer.QuestionActivity.KEY_NAME;

public class BookmarkActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    BookmarksApdater bookmarksApdater;


    private List<QuestionModel> bookmarksList;

    private SharedPreferences preferences;
    private  SharedPreferences.Editor editor;
    private Gson gson;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);


        loadAds();
        toolbar=findViewById(R.id.toolbar);

        recyclerView=findViewById(R.id.rv_bookmarks);

        preferences=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor=preferences.edit();
        gson=new Gson();

        getBookmarks();


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        layoutManager=new LinearLayoutManager(this);

        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);



        bookmarksApdater=new BookmarksApdater(bookmarksList);

        recyclerView.setAdapter(bookmarksApdater);

    }


    @Override
    protected void onPause() {
        super.onPause();

        storeBookmarks();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }







    private void getBookmarks()
    {
        // value retrive  kr li from preference and store kr di bookrmarkslist varibale

        // Retrieve a String value from the preferences.

        String json=preferences.getString(KEY_NAME,"");

        Type type=new TypeToken<List<QuestionModel>>(){}.getType();


        // value store in bookmarlist  using gosn ki  help sy
        bookmarksList=gson.fromJson(json,type);



        if (bookmarksList==null)
        {
            bookmarksList=new ArrayList<>();
        }
    }



    private void  storeBookmarks() {
        // list store
        String json = gson.toJson(bookmarksList);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }




    private void loadAds()
    {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}