package com.mta.lennit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    TextView tv;
    EditText name;
    Button view;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent obj=getIntent();
        String name1=obj.getExtras().getString("name");
        tv=(TextView)findViewById(R.id.textView4);
        view=(Button)findViewById(R.id.button9);
        tv.setText("welcome  "+name1);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM users", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("full_name: " + c.getString(1) + "\n");
            buffer.append("Email: " + c.getString(3) + "\n");
            buffer.append("Contact: " + c.getString(4) + "\n");
            buffer.append("Pin_code: " + c.getString(5) + "\n");
            buffer.append("Date-joined: " + c.getString(6) + "\n");

        }
        showMessage("Student Details", buffer.toString());
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


            }

        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent obj = new Intent(profile.this, settings.class);
            startActivity(obj);
            return true;
        } else if (id == R.id.logout) {
            Toast.makeText(getApplicationContext(), "You have successfully  Logout.", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
