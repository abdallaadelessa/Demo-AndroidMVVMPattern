package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abdulla on 10/31/15.
 */
public class User extends BaseObservable implements Parcelable {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyChange();
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // ------------------->


    // Parcelling part
    public User(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.username = data[0];
        this.password = data[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.username, this.password});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
