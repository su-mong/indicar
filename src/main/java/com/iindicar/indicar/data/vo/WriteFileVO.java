package com.iindicar.indicar.data.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.iindicar.indicar.BR;


/**
 * Created by yeseul on 2018-05-02.
 */

public class WriteFileVO extends BaseObservable implements Parcelable{

    private Uri imageUrl;
    private String filePath = "";
    private String writeText = "";
    private String fileIndex;

    public WriteFileVO(){

    }

    protected WriteFileVO(Parcel in) {
        imageUrl = in.readParcelable(Uri.class.getClassLoader());
        filePath = in.readString();
        writeText = in.readString();
        fileIndex = in.readString();
    }

    public static final Creator<WriteFileVO> CREATOR = new Creator<WriteFileVO>() {
        @Override
        public WriteFileVO createFromParcel(Parcel in) {
            return new WriteFileVO(in);
        }

        @Override
        public WriteFileVO[] newArray(int size) {
            return new WriteFileVO[size];
        }
    };

    @Bindable
    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        notifyPropertyChanged(BR.filePath);
    }

    @Bindable
    public String getWriteText() {
        return writeText;
    }

    public void setWriteText(String writeText) {
        this.writeText = writeText;
        notifyPropertyChanged(BR.writeText);
    }

    public String getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(String fileIndex) {
        this.fileIndex = fileIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(imageUrl, i);
        parcel.writeString(filePath);
        parcel.writeString(writeText);
        parcel.writeString(fileIndex);
    }
}
