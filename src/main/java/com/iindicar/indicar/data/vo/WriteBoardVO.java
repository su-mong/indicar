package com.iindicar.indicar.data.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.iindicar.indicar.BR;

/**
 * Created by yeseul on 2018-05-02.
 */

public class WriteBoardVO extends BaseObservable implements Parcelable{

    @SerializedName("bbs_id") private String boardType;
    @SerializedName("ntt_id") private String boardId;
    @SerializedName("ntcr_nm") private String userName;
    @SerializedName("ntcr_id") private String userId;
    @SerializedName("atch_file_id") private String[] fileIndex;

    public WriteBoardVO(){

    }
    
    protected WriteBoardVO(Parcel in) {
        boardType = in.readString();
        boardId = in.readString();
        userName = in.readString();
        userId = in.readString();
        fileIndex = in.createStringArray();
    }

    public static final Creator<WriteBoardVO> CREATOR = new Creator<WriteBoardVO>() {
        @Override
        public WriteBoardVO createFromParcel(Parcel in) {
            return new WriteBoardVO(in);
        }

        @Override
        public WriteBoardVO[] newArray(int size) {
            return new WriteBoardVO[size];
        }
    };

    @Bindable
    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
        notifyPropertyChanged(BR.boardType);
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(String[] fileIndex) {
        this.fileIndex = fileIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(boardType);
        parcel.writeString(boardId);
        parcel.writeString(userName);
        parcel.writeString(userId);
        parcel.writeStringArray(fileIndex);
    }
}
