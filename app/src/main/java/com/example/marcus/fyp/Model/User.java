package com.example.marcus.fyp.Model;

public class User {
    private String Password1;
    private String Email;
    private String phoneNumber;
    private String SeriesNO;

   public User(){

   }

    public User(String password, String email, String PhoneNumber, String seriesNo) {

        Password1 = password;
        Email = email;
        phoneNumber=PhoneNumber;
        SeriesNO=seriesNo;
    }

    public String getPassword() {
        return Password1;
    }

    public void setPassword(String password) {
        Password1 = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getseriesNO() {
        return SeriesNO;
    }

    public void setseriesNO(String seriesNO) {
        this.SeriesNO = seriesNO;
    }
}

