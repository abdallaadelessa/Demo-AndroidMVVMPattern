package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by abdulla on 10/31/15.
 */
public class User extends BaseObservable {
    private BindableString username;
    private BindableString password;

    public User() {
    }

    public User(BindableString username, BindableString password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(BindableString username) {
        this.username.set(username.get());
    }

    public void setPassword(BindableString password) {
        this.password.set(password.get());
    }

    @Bindable
    public BindableString getUsername() {
        return username;
    }

    @Bindable
    public BindableString getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username.get() + '\'' +
                ", password='" + password.get() + '\'' +
                '}';
    }

    // ------------------->


    // Parcelling part
    //    public User(Parcel in) {
    //        String[] data = new String[2];
    //        in.readStringArray(data);
    //        this.username = data[0];
    //        this.password = data[1];
    //    }
    //
    //    @Override
    //    public int describeContents() {
    //        return 0;
    //    }
    //
    //    @Override
    //    public void writeToParcel(Parcel dest, int flags) {
    //        dest.writeStringArray(new String[]{this.username, this.password});
    //    }
    //
    //    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    //        public User createFromParcel(Parcel in) {
    //            return new User(in);
    //        }
    //
    //        public User[] newArray(int size) {
    //            return new User[size];
    //        }
    //    };
}
