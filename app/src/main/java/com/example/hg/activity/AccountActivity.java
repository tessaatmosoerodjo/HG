package com.example.hg.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.hg.R;
import com.example.hg.db.Database;
import com.example.hg.entity.Customer;
import com.example.hg.loginernreg.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "Account";
    private Toolbar toolbar;
    private Button updateBtn, deleteBtn;
    private TextInputEditText dialogPassword, accountName, accountStreet, accountPhone, accountPlace;
    private TextInputLayout dialogPasswordLayout;
    private Database databaseHelper;
    public Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new Database(getApplicationContext());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.account);


        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);


        accountName = findViewById(R.id.accountName);
        accountPhone = findViewById(R.id.accountPhone);
        accountStreet = findViewById(R.id.accountStreet);
        accountPlace = findViewById(R.id.accountPlace);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdate();
            };
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount();
            }
        });
    }





    @Override
    public void onStart() {
//        customer = intent.getParcelableExtra("loggedInCustomer");


//        if (customer!=null){
            Customer currentCustomer = databaseHelper.getCustomerByID(customer.getId());

//        Intent intent = getIntent();

            accountName.setText(String.format("%s %s", currentCustomer.getName()));
            accountPhone.setText(currentCustomer.getPhone());
            accountStreet.setText(currentCustomer.getStreet());
            accountPlace.setText(currentCustomer.getPlace());
//        }
        super.onStart();
    }


    private void goToUpdate() {
        Intent intent = new Intent(this, UpdateUserActivity.class);
        startActivity(intent);
    }



    private void deleteAccount(){

        final boolean deleteRequest = databaseHelper.deleteUser(customer.getId());
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteContact)

                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (deleteRequest) {
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Er is iets misgegaan", Toast.LENGTH_SHORT).show();
                    }
                });
        android.app.AlertDialog d = builder.create();
        d.setTitle("Are you sure ?");
        d.show();



    }


}
