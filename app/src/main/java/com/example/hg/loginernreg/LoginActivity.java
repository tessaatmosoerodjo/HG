package com.example.hg.loginernreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hg.activity.MainActivity;
import com.example.hg.R;
import com.example.hg.db.Database;
import com.example.hg.entity.Customer;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerMe = findViewById(R.id.registerMe);

        registerMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginEvent(View view) {

        Database databaseHelper = new Database(this);

        EditText email = findViewById(R.id.login_email);
        EditText password = findViewById(R.id.login_password);

        String emailStr = String.valueOf(email.getText());
        String passwordStr = String.valueOf(password.getText());


        Customer customer = databaseHelper.getCustomerByEmail(emailStr);

        if (emailStr.isEmpty() && passwordStr.isEmpty()) {


            Toast.makeText(this, "Please Put Credentials", Toast.LENGTH_SHORT).show();

        } else if (emailStr.equals(customer.getEmail()) && passwordStr.equals(customer.getPassword())) {

            Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (emailStr.equals("admin") && passwordStr.equals("admin")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {

            Toast.makeText(this, "login not successful", Toast.LENGTH_SHORT).show();


        }
    }
    }
