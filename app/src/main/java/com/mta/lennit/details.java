package com.mta.lennit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class details extends AppCompatActivity {
ListView details;
    String[] detailname;
    ArrayAdapter<String> detailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
        detailname=new String[2];
        Intent obj=getIntent();
        String p1=obj.getExtras().getString("people");
        if(p1.equalsIgnoreCase("swapnil"))
        {
            detailname[0]="swapnilbhatt97@gmail.com";
            detailname[1]="8765542184";
        }
        else if(p1.equalsIgnoreCase("sakshi"))
        {
            detailname[0]="sakshi23@gmail.com";
            detailname[1]="9876543210";
        }
        else if (p1.equalsIgnoreCase("yashi"))
        {
            detailname[0]="yashibhatt98@gmail.com";
            detailname[1]="8798767780";
        }
        detailAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_list_item_1,detailname);
        details=(ListView)findViewById(R.id.listView2);
        details.setAdapter(detailAdapter);

    }

}
