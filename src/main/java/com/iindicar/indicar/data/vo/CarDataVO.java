package com.iindicar.indicar.data.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by candykick on 2018. 7. 9..
 */

public class CarDataVO extends BaseObservable {
    private int carDataImgIndex;
    @SerializedName("car_name") private String carDataName;
    @SerializedName("car_name_kor") private String carDataNameKor;
    @SerializedName("company") private String carDataCompany;
    @SerializedName("company_kor") private String carDataCompanyKor;
    @SerializedName("img_url") private String carDataImageUrl;

    public CarDataVO(){

    }

    public CarDataVO(String carDataName, int carDataImgIndex) {
        this.carDataName = carDataName;
        this.carDataImgIndex = carDataImgIndex;
    }

    @Override
    public String toString() {
        return "CarDataVO{" + "carDataImgIndex=" + carDataImgIndex + ", carDataName='" + carDataName + '\'' + ", carDataNameKor='" + carDataNameKor + '\'' + ", carDataCompany='" + carDataCompany + '\'' + ", carDataCompanyKor='" + carDataCompanyKor + '\'' + ", carDataImageUrl='" + carDataImageUrl + '\'' + '}';
    }

    public String getCarDataName() {
        return carDataName;
    }

    public void setCarDataName(String carDataName) {
        this.carDataName = carDataName;
    }

    public String getCarDataNameKor() {
        return carDataNameKor;
    }

    public void setCarDataNameKor(String carDataNameKor) {
        this.carDataNameKor = carDataNameKor;
    }

    public int getCarDataImgIndex() {
        return carDataImgIndex;
    }

    public void setCarDataImgIndex(int carDataImgIndex) {
        this.carDataImgIndex = carDataImgIndex;
    }

    public String getCarDataCompany() {
        return carDataCompany;
    }

    public void setCarDataCompany(String carDataCompany) {
        this.carDataCompany = carDataCompany;
    }

    public String getCarDataCompanyKor() {
        return carDataCompanyKor;
    }

    public void setCarDataCompanyKor(String carDataCompanyKor) {
        this.carDataCompanyKor = carDataCompanyKor;
    }

    @Bindable
    public String getCarDataImageUrl() {
        return carDataImageUrl;
    }

    public void setCarDataImageUrl(String carDataImageUrl) {
        this.carDataImageUrl = carDataImageUrl;
        notifyPropertyChanged(BR.carDataImageUrl);
    }
}
