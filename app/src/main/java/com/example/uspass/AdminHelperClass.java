package com.example.uspass;

public class AdminHelperClass {

    String AdminUserId, AdminPassword;

    public AdminHelperClass(String adminUserId, String adminPassword) {
        AdminUserId = adminUserId;
        AdminPassword = adminPassword;
    }

    public AdminHelperClass() {
    }

    public String getAdminUserId() {
        return AdminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        AdminUserId = adminUserId;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }
}
