package com.example.xchange;

public class User {
    public static final String TABLE_NAME="users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_BIRTHDATE = "birthDate";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FIRSTNAME + " TEXT"
                    + COLUMN_LASTNAME + " TEXT"
                    + COLUMN_EMAIL + " TEXT"
                    + COLUMN_PASSWORD + " TEXT"
                    + COLUMN_BIRTHDATE + " TEXT"
                    + ")";
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;

    public User(Integer id,String firstName, String lastName, String email, String password, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
