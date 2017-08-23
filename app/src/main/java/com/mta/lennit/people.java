package com.mta.lennit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class people extends AppCompatActivity {
    ListView people;
    String[] peoplename;
    ArrayAdapter<String> aa1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
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
        peoplename=new String[6];
        peoplename[0]="HR";
        peoplename[1]="Software Development";
        peoplename[2]="Engineering";
        peoplename[3]="MBA Entrance";
        peoplename[4]="MBA placement";
        peoplename[5]="Company Wise";
        aa1=new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_list_item_1,peoplename);
        people=(ListView)findViewById(R.id.listView);
        people.setAdapter(aa1);
        people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedpeople=people.getItemAtPosition(position).toString();
                Intent obj=new Intent(people.this,details.class);
                obj.putExtra("people",selectedpeople);
                startActivity(obj);
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                get_states();
            }
        });
        t.start();
    }

    public void get_states()
    {
        httpconnection obj=new httpconnection();
        String url="http://192.168.43.225:81/swap/get_states.php";
        List<BasicNameValuePair> l1 = new ArrayList<BasicNameValuePair>();
        l1.add(new BasicNameValuePair("name", "amit"));
        final String result=obj.get_httpvalue(url,l1,getApplicationContext());


         /* *********** json parsing *****
            jArray = new JSONArray(result);
            JSONObject json_data = null;

            final int length = jArray.length();
            peoplename = new String[length];

            int j = 0;
            for (int i = 0; i < length; i++) {
                json_data = jArray.getJSONObject(i);
                peoplename[i] = json_data.getString("state_name");

            }
        } catch (Exception e) {
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aa1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_1, peoplename);
                people = (ListView) findViewById(R.id.listView);
                people.setAdapter(aa1);
            }
        });

    }
}


