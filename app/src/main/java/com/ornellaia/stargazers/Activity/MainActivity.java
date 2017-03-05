package com.ornellaia.stargazers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.ornellaia.stargazers.R;


public class MainActivity extends AppCompatActivity {

    private EditText ownerEditText, repoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ownerEditText = ((EditText) findViewById(R.id.insert_owner));
        repoEditText =((EditText) findViewById(R.id.insert_repo));
        Button start = (Button) findViewById(R.id.start_button);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ownerEditText.setHint("");
                repoEditText.setHint("");
                Intent intent = new Intent(getApplicationContext(), StargazersMatrix.class);
                intent.putExtra("owner", ownerEditText.getText().toString());
                intent.putExtra("repository", repoEditText.getText().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
