package com.mta.lennit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class register extends ActionBarActivity {
    EditText name,email,password,contact,pin;
    Button register1;
    Thread t;
    TextView msg;
    String unamee,emaill,passwordd,contactt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.editText4);
        email=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);
        contact=(EditText)findViewById(R.id.editText3);
        register1=(Button)findViewById(R.id.button2);
        msg=(TextView) findViewById(R.id.textView);
        pin=(EditText)findViewById(R.id.editText5);

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()) {
                    t = new Thread() {
                        public void run() {

                            registration();
                        }

                    };
                    t.start();
                }
            }
        });


        /*
        try {

            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
                //textInstructionsOrLink = (TextView)findViewById(R.id.textstring);
                //textInstructionsOrLink.setText(sign);
                Toast.makeText(getApplicationContext(), sign, Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("nope", "nope");
        } catch (NoSuchAlgorithmException e) {
        }
        */
    }
    public void registration()
    {
        /*runOnUiThread(new Runnable() {

            public void run() {
                Toast.makeText(Registration.this, "Register Successfully.", Toast.LENGTH_SHORT).show();

            }
        });*/
        String status="";
        String rsid="";
        String remail="";
        String rname="";
        httpconnection obj=new httpconnection();
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("uname",name.getText().toString()));
        nvps.add(new BasicNameValuePair("password",password.getText().toString()));
        nvps.add(new BasicNameValuePair("contact",contact.getText().toString()));
        nvps.add(new BasicNameValuePair("email",email.getText().toString()));
        nvps.add(new BasicNameValuePair("pin",pin.getText().toString()));

        final String result=obj.get_httpvalue("http//192.168.43.225:81/swap/register.php", nvps,getApplicationContext());

        JSONArray jArray;
        try {
            jArray = new JSONArray(result);
            JSONObject json_data = null;

            final int daycount=jArray.length();

            for(int i=0;i<daycount;i++)
            {
                json_data = jArray.getJSONObject(i);
                status= json_data.getString("status");

            }
            if(status.equalsIgnoreCase("fail")) {
                runOnUiThread(new Runnable() {

                    public void run() {
                        msg.setText("Email Id Already in Use.");
                    }
                });
            }
            else
            {
                runOnUiThread(new Runnable() {

                    public void run() {
                        //Toast.makeText(register.this, "Register Successfully.", Toast.LENGTH_SHORT).show();
                       // finish();
                        Intent obj=new Intent(register.this,start.class);
                        startActivity(obj);
                    }
                });

            }

        }
        catch(Exception e)
        {
        }





    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }*/
    private boolean checkValidation() {
        boolean ret = true;

        if (!com.mta.lennit.Validation.hasText(name)) ret = false;
        if (!com.mta.lennit.Validation.hasText(password)) ret = false;
        if (!com.mta.lennit.Validation.isEmailAddress(email, true)) ret = false;
        if (!com.mta.lennit.Validation.isPhoneNumber(contact, true)) ret = false;
        if (!com.mta.lennit.Validation.hasText(pin)) ret = false;


        return ret;
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