package com.example.hg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hg.R;
import com.example.hg.db.Database;
import com.example.hg.loginernreg.LoginActivity;

public class Profile extends AppCompatActivity {


    int from_Where_I_Am_Coming = 0;
     Database mydb;
    TextView name;
    TextView phone;
//    TextView email;
    TextView street;
    TextView place;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = (TextView) findViewById(R.id.update_name);
        phone = (TextView) findViewById(R.id.update_phone);
        street = (TextView) findViewById(R.id.update_street);
        place = (TextView) findViewById(R.id.update_place);

        mydb = new Database(this);

        Bundle extras = getIntent().getExtras();
        {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.
//
//                Cursor rs = mydb.getData(Value);
//                id_To_Update = Value;
//
//              //  rs.moveToNext();
//                    // retrieve next set of values from the cursor
//                if (rs.moveToNext()) {
//                   String stname = rs.getString(rs.getColumnIndex(Database.NAME));
//                   String stphone = rs.getString(rs.getColumnIndex(Database.PHONE));
//                   String ststreet = rs.getString(rs.getColumnIndex(Database.STREET));
//                   String stplace = rs.getString(rs.getColumnIndex(Database.PLACE));
//
//
//                   if (!rs.isClosed()) {
//                       rs.close();
//                   }
//                   Button b = (Button) findViewById(R.id.button1);
//                   b.setVisibility(View.INVISIBLE);
//
//
//                   name.setText(stname);
//                   name.setFocusable(false);
//                   name.setClickable(false);
//
//
//                   phone.setText(stphone);
//                   phone.setFocusable(false);
//                   phone.setClickable(false);
//
//                   street.setText(ststreet);
//                   street.setFocusable(false);
//                   street.setClickable(false);
//
//                   place.setText(stplace);
//                   place.setFocusable(false);
//                   place.setClickable(false);
//
//                   rs.close();



            }
        }
    }

    private void updateUser(){
        asyncUpdateUser updateUserTask = new asyncUpdateUser();
    }


    private class asyncUpdateUser extends AsyncTask <ContentValues, Integer, String> {

        Database database;


        @Override
        protected String doInBackground(ContentValues... contentValues) {


            return null;
        }


    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.menu_display, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_add, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);


                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);

                place.setEnabled(true);
                place.setFocusableInTouchMode(true);
                place.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure ?");
                d.show();

                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void saveData(View view) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateStudentContact(id_To_Update, name.getText().toString(), phone.getText().toString(), street.getText().toString(), place.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Record not updated", Toast.LENGTH_SHORT).show();
                }
            } else {
////                if (mydb.addStudentContact(name.getText().toString(), phone.getText().toString(),street.getText().toString(), place.getText().toString())) {
//                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Record not added", Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
