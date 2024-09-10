package com.example.cocoagh.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private int id, userType;
    private String name, username, phone, password;

    public Users(){}

    public Users(int id, String name, String username, String phone, String password, int userType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    protected Users(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        phone = in.readString();
        userType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeInt(userType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
