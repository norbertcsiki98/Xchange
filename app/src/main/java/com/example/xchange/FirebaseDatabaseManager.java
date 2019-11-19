package com.example.xchange;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseDatabaseManager {
    public static ArrayList<User> users=new ArrayList<>();

    public FirebaseDatabaseManager()
    {

    }

    public static  class Instance {
        static FirebaseDatabase database = FirebaseDatabase.getInstance();
        static DatabaseReference usersReferences = database.getReference().child("Users");


        public static String CreateNewUser(String firstName, String lastName, String email, String password, String birthDate) {
            User user = new User(firstName, lastName, email, password, birthDate);
            String key = usersReferences.push().getKey();
            usersReferences.child(key).setValue(user);
            return key;
        }
    }
}
