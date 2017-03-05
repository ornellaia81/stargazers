package com.ornellaia.stargazers.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.ornellaia.stargazers.Adapter.StargazersAdapter;
import com.ornellaia.stargazers.R;
import com.ornellaia.stargazers.util.HandleJSON;

import java.util.ArrayList;

/**
 * Created by ornella on 03/03/17.
 */
public class StargazersMatrix extends AppCompatActivity {

    public String apiHostname = "https://api.github.com/";
    public String TAG = "StargazersMatrix";
    private ArrayList<String> avatars;
    private ArrayList<String> users = null;

    public StargazersMatrix() {
        super();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgazers_layout);

        if (getIntent() != null) {
            String owner = getIntent().getStringExtra("owner").trim();
            Log.d(TAG, owner);
            String repo = getIntent().getStringExtra("repository").trim();
            Log.d(TAG, repo);
            String finalUrl = apiHostname + "repos/" + owner + "/" + repo + "/stargazers";
            Log.d(TAG, "api url: " + finalUrl);

            HandleJSON obj = new HandleJSON(finalUrl);

            GridView myList = (GridView) findViewById(R.id.stargazers_list);
            try {
                obj.fetchJSON();
                while (obj.parsingComplete) ;
                avatars = (obj.getAvatarUrl());
                users = (obj.getUsers());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (users.size() > 0) {
                StargazersAdapter myAdapter = new StargazersAdapter(this, users, avatars);
                myList.setAdapter(myAdapter);
            } else {
                myList.setVisibility(View.GONE);
                findViewById(R.id.no_stars_layout).setVisibility(View.VISIBLE);
            }


            Log.d("TAG", "users number: " + users.size() + "avatars number: " + avatars.size());
        }


    }


}
