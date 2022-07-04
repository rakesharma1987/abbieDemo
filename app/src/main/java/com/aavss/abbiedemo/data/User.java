package com.aavss.abbiedemo.data;

import java.io.Serializable;

public class User{
    private String firstName;
    private String lastName;
    private String mobile;
    private String address;
    private String rowId;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowId(){
        return rowId;
    }
}
