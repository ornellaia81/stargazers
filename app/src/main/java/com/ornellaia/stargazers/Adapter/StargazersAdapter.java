package com.ornellaia.stargazers.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ornellaia.stargazers.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ornella on 03/03/17.
 */

public class StargazersAdapter extends BaseAdapter {

    private final Activity activity;
    ;

    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> avatars = new ArrayList<>();
    private String name, url;

    public StargazersAdapter(Activity value, ArrayList<String> users, ArrayList<String> avatars) {
        super();
        activity = value;
        this.users = users;
        this.avatars = avatars;
    }

    @Override
    public int getCount() {
        try {
            return users.size();
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.stargazer_item, null);
        name = users.get(position);
        url = avatars.get(position);
        ((TextView) convertView.findViewById(R.id.name_stargazer)).setText(name);
        Picasso.with(activity).load(url).into(((ImageView) convertView.findViewById(R.id.avatar_stargazer)));

        return convertView;
    }
}
