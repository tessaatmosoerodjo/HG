package com.example.hg.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hg.entity.Customer;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "hellogorgeous.db";
    private static final String TABLE_CUSTOMERS = "customer_table";
    private static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String STREET = "street";
    public static final String PLACE = "place";



    private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " (id integer primary key not null ," +
            " name string not null , email string not null , password string not null, phone string not null, street string not null, place string not null)";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public boolean insertUser(ContentValues contentValues) {

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_CUSTOMERS, null, contentValues) > 0;

    }


    public Customer getCustomerByEmail(String email) {

        Customer customer = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS, null, EMAIL + " = ?", new String[]{"" + email},
                null, null, null);
        if (cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String street = cursor.getString(cursor.getColumnIndex(STREET));
            String place = cursor.getString(cursor.getColumnIndex(PLACE));

            customer = new Customer(id, name, email, password, phone, street, place);
        }
        cursor.close();
        db.close();

        return customer;
    }


    public boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int deleteQuery;
        try {
            deleteQuery = db.delete(TABLE_CUSTOMERS, "id=? ", new String[]{String.valueOf(id)});
            return deleteQuery > 0;
        }catch (SQLiteException e){
            return false;
        }

    }

    public Customer getCustomerByID(int id) {

        Customer customer = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,email,name,phone,street,place FROM customer_table WHERE  id = ?", null);
        if (cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String street = cursor.getString(cursor.getColumnIndex(STREET));
            String place = cursor.getString(cursor.getColumnIndex(PLACE));


            customer = new Customer(id, name, email, password, phone, street, place);
        }
        cursor.close();
        db.close();

        return customer;
    }


    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select id, name, email, phone, street, place from customer_table where id = ? ", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 0) {
            return null;
        } else {
            cursor.moveToFirst();
            return new Customer(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)

            );
        }
    }

    public boolean updateCustomer(ContentValues contentValues){
        SQLiteDatabase db = getWritableDatabase();
        try {
            int updateQuery = db.update(TABLE_CUSTOMERS, contentValues, "id= ?", new String[]{String.valueOf(1)});
            return updateQuery > 0;
        }catch (SQLiteException e){
            return false;
        }
    }




    public boolean updateStudentContact(Integer id, String name, String phone, String street, String place) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("customer_table", contentValues, "id = ?", new String[]{Integer.toString(id)});
        db.close();
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("customer_table", "id = ?", new String[]{Integer.toString(id)});
    }



    public ArrayList<String> getAllStudentContacts() {
        ArrayList<String> arraylist = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from customer_table", null);

        if (cursor.moveToFirst()) {
            do {
                arraylist.add(cursor.getString(cursor.getColumnIndex(NAME)));
            } while (cursor.moveToNext());
        }
        return arraylist;
    }

}