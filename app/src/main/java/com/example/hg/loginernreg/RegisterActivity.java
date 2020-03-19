package com.example.hg.loginernreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hg.R;
import com.example.hg.db.Database;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.hg.db.Database.EMAIL;
import static com.example.hg.db.Database.NAME;
import static com.example.hg.db.Database.PASSWORD;
import static com.example.hg.db.Database.PHONE;
import static com.example.hg.db.Database.PLACE;
import static com.example.hg.db.Database.STREET;


public class RegisterActivity extends AppCompatActivity {

private TextInputLayout textInputEmail;
private TextInputLayout textInputName;
private TextInputLayout textInputPassword;
private TextInputLayout textInputConfirmPassword;
private TextInputLayout textInputphone;
private TextInputLayout textInputstreet;
private TextInputLayout textInputplace;



        @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        }

private boolean validateName(){
        String nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()){
        textInputName.setError("Field can't be empty");
        return false;
        }
        else{
        textInputName.setError(null);
        return true;

        }
        }


private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
        textInputEmail.setError("Field can't be empty");
        return false;
        }
        else{
        textInputEmail.setError(null);
        return true;

        }
        }

private boolean validatePassword(){
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
        textInputPassword.setError("Field can't be empty");
        return false;
        }else{
        textInputPassword.setError(null);
        return true;

        }
        }

private boolean validateConfirmPassword(){
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        if (confirmPasswordInput.isEmpty()){
        textInputConfirmPassword.setError("Field can't be empty");
        return false;
        }
        else{
        textInputConfirmPassword.setError(null);
        return true;

        }
        }

public void RegisterEvent(View view) {

        textInputEmail = findViewById(R.id.text_input_reg_email);
        textInputName = findViewById(R.id.text_input_reg_name);
        textInputPassword = findViewById(R.id.text_input_reg_password);
        textInputConfirmPassword = findViewById(R.id.text_input_reg_cfm_password);
        textInputphone = findViewById(R.id.text_input_reg_phone);
        textInputstreet = findViewById(R.id.text_input_reg_street);
        textInputplace = findViewById(R.id.text_input_reg_place);


        if (!validateEmail() | !validateName() | !validatePassword() | !validateConfirmPassword()){

        return;
        }

        String nameStr= textInputName.getEditText().getText().toString();
        String emailStr= textInputEmail.getEditText().getText().toString();
        String passwordStr=textInputPassword.getEditText().getText().toString();
        String passwordConfStr= textInputConfirmPassword.getEditText().getText().toString();
        String phoneStr = textInputphone.getEditText().getText().toString();
        String streetStr= textInputstreet.getEditText().getText().toString();
        String placeStr= textInputplace.getEditText().getText().toString();


        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,nameStr);
        contentValues.put(EMAIL,emailStr);
        contentValues.put(PASSWORD,passwordStr);
        contentValues.put(PHONE,phoneStr);
        contentValues.put(STREET,streetStr);
        contentValues.put(PLACE,placeStr);

        Database dao= new Database(this);
        if (!passwordStr.equals(passwordConfStr)) {
        Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show();
        } else if(dao.insertUser(contentValues)){
        Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        }else{
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }


        }
        }

