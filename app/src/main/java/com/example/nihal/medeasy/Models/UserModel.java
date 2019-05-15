package com.example.nihal.medeasy.Models;

public class UserModel {

    private String UserName, YearOfBirth, Address, Occupation, FamilyHistoryLink, Weight, Height, Password, PhoneNumber;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getFamilyHistoryLink() {
        return FamilyHistoryLink;
    }

    public void setFamilyHistoryLink(String familyHistoryLink) {
        FamilyHistoryLink = familyHistoryLink;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getYearOfBirth() {
        return YearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }

    public UserModel(String userName, String address, String occupation, String familyHistoryLink, String weight, String height, String yearOfBirth, String password, String phonNumber) {
        UserName = userName;
        Address = address;
        Occupation = occupation;
        FamilyHistoryLink = familyHistoryLink;
        Weight = weight;
        Height = height;
        YearOfBirth = yearOfBirth;
        Password = password;
        PhoneNumber = phonNumber;
    }

    public UserModel() {

    }
}

