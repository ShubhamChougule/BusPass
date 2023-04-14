package com.example.uspass;

import java.util.ArrayList;
import java.util.List;

public class HelperClass {
    String fullName, address, pinCode,phoneNo, emailId,passWord, age,  collegeName;
    String passDays;
    String passType;
    String oneMark, twoMark;
    String startPosition, endPosition;

    public HelperClass(String fullName, String address, String pinCode, String phoneNo, String emailId, String passWord, String age, String collegeName, String passDays, String passType, String oneMark, String twoMark, String startPosition, String endPosition) {
        this.fullName = fullName;
        this.address = address;
        this.pinCode = pinCode;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.passWord = passWord;
        this.age = age;
        this.collegeName = collegeName;
        this.passDays = passDays;
        this.passType = passType;
        this.oneMark = oneMark;
        this.twoMark = twoMark;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public String getFullName() {
        return fullName;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getPassDays() {
        return passDays;
    }

    public void setPassDays(String passDays) {
        this.passDays = passDays;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public String getOneMark() {
        return oneMark;
    }

    public void setOneMark(String oneMark) {
        this.oneMark = oneMark;
    }

    public String getTwoMark() {
        return twoMark;
    }

    public void setTwoMark(String twoMark) {
        this.twoMark = twoMark;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public HelperClass() {
    }
}
