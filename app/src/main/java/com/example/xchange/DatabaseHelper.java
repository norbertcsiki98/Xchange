package com.example.xchange;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db";
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }

    public long insertUser(String firstName,String lastName,String email,String password, String birthDate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(User.COLUMN_FIRSTNAME,firstName);
        values.put(User.COLUMN_LASTNAME,lastName);
        values.put(User.COLUMN_EMAIL,email);
        values.put(User.COLUMN_PASSWORD,password);
        values.put(User.COLUMN_BIRTHDATE,birthDate);
        long id = db.insert(User.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public User getUser(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_FIRSTNAME,User.COLUMN_LASTNAME,User.COLUMN_EMAIL,User.COLUMN_PASSWORD,User.COLUMN_BIRTHDATE},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_FIRSTNAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_LASTNAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_BIRTHDATE)));
        cursor.close();
        return user;
    }

    public List<User>getAllUser()
    {
        List<User> users=new ArrayList<>();
        String selectQuery = "SELECT * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(User.COLUMN_FIRSTNAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(User.COLUMN_LASTNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                user.setBirthDate(cursor.getString(cursor.getColumnIndex(User.COLUMN_BIRTHDATE)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return users;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
