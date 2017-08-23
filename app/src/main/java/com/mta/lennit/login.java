package com.mta.lennit;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class login extends Activity {

    EditText roll,name1,num1,num2;
    Button add,view,modify,delete;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        num1=(EditText)findViewById(R.id.editTexts5);
        num2=(EditText)findViewById(R.id.editTexts6);
        modify=(Button)findViewById(R.id.buttons1);
		/*delete=(Button)findViewById(R.id.button4);*/
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

        modify.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0) {
                if(num1.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter username");
                    return;
                }
                if(num2.getText().toString().length()==0)
                {
                    showMessage("Error","please enter your password");
                    return;
                }
                Cursor c1=db.rawQuery("SELECT * FROM users WHERE email='"+num1.getText()+"' and user_pass ='"+num2.getText()+"'",null);

                if(c1.moveToFirst())
                {
                    Intent obj=new Intent(login.this,profile.class);
                    obj.putExtra("name", num1.getText().toString());
                    startActivity(obj);
                    clearText();
                }
                else
                {
                    showMessage("Error", "Invalid username and password");
                }
                clearText();

            }

        });


		/*delete.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				if(roll.getText().toString().trim().length()==0)
	    		{
	    			showMessage("Error", "Please enter Rollno");
	    			return;
	    		}
	    		Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+roll.getText()+"'", null);
	    		if(c.moveToFirst())
	    		{
	    			db.execSQL("DELETE FROM student WHERE rollno='"+roll.getText()+"'");
	    			showMessage("Success", "Record Deleted");
	    		}
	    		else
	    		{
	    			showMessage("Error", "Invalid Rollno");
	    		}
	    		clearText();

			}

		});*/



    }

	/*public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}*/

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        num1.setText("");
        num2.setText("");

    }

}
