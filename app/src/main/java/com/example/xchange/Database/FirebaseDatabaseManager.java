package com.example.xchange.Database;

public class FirebaseDatabaseManager {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String EMAIL;
    private String PASSWORD;
    private String BIRTH_DATE;

    public FirebaseDatabaseManager()
    {

    }

    public FirebaseDatabaseManager(String FIRST_NAME, String LAST_NAME, String EMAIL, String PASSWORD, String BIRTH_DATE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(String BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }
}
