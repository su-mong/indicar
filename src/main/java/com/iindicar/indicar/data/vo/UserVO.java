package com.iindicar.indicar.data.vo;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.iindicar.indicar.BR;

/**
 * Created by yeseul on 2018-04-14.
 */

public class UserVO extends BaseObservable{

    @SerializedName("id") private String userId = "";
    @SerializedName("name") private String userName = "";
    @SerializedName("email") private String userEmail = "";
    @SerializedName("profile_img_url") private String profileImageUrl = "";
    @SerializedName("address") private String userAddress = "";

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userKey);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        notifyPropertyChanged(BR.profileImageUrl);
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
