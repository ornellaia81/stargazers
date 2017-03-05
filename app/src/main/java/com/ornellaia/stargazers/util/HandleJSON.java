package com.ornellaia.stargazers.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;

import android.util.Log;

import javax.net.ssl.HttpsURLConnection;

public class HandleJSON {

    public ArrayList<String> url_avatars = new ArrayList<>();
    public ArrayList<String> login_users = new ArrayList<>();
    private Runnable runnable_job = null;
    private String urlString = "";
    private String TAG = "HandleJSON";

    public volatile boolean parsingComplete = true;

    public HandleJSON(String url) {
        this.urlString = url;
    }

    public ArrayList<String> getUsers() {
        return login_users;
    }

    public ArrayList<String> getAvatarUrl() {
        return url_avatars;
    }


    public void fetchJSON() {

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = getConnection(url);
                    int status = connection.getResponseCode();

                    if (status == 404) {
                        connection.disconnect();
                        parsingComplete = false;
                        return;
                    } else {

                        InputStream stream = connection.getInputStream();
                        if (stream != null) {
                            String data = convertStreamToString(stream);
                            JSONArray users = new JSONArray(data);
                            readAndParseJSON(users);
                            stream.close();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });

        thread.start();
    }

    public HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn;
    }

    public ArrayList<String> readAndParseJSON(JSONArray users) {
        try {
            for (int i = 0; i < users.length(); i++) {
                login_users.add(users.getJSONObject(i).getString("login"));
                url_avatars.add(users.getJSONObject(i).getString("avatar_url"));
                Log.d(TAG, "User: " + users.getJSONObject(i).getString("login") + " Url image: " + users.getJSONObject(i).getString("avatar_url"));
            }

            parsingComplete = false;


        } catch (Exception e) {
            e.printStackTrace();

        }
        return login_users;
    }


    public static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}