package com.example.uspass;

public class HelperClass {
    String fullName, address, pinCode,phoneNo, emailId,passWord, age,  collegeName;
    Boolean isMale;

    public HelperClass(String fullName, String address, String pinCode, String phoneNo, String emailId, String passWord, String age, String collegeName, Boolean isMale) {
        this.fullName = fullName;
        this.address = address;
        this.pinCode = pinCode;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.passWord = passWord;
        this.age = age;
        this.collegeName = collegeName;
        this.isMale = isMale;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public HelperClass() {
    }
}
