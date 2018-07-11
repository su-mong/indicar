package com.iindicar.indicar.data.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.iindicar.indicar.BR;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yeseul on 2018-04-13.
 *
 *  TODO. (2018.05.02) 제목, 내용 필드는 없애야됨!!
 */

public class BoardVO extends BaseObservable implements Parcelable {

    private String userProfileUrl = "";
    private String mainImageUrl = "";
    private String mainImageWidth = "";
    private String mainImageHeight = "";
    @SerializedName("atch_file_id") private String[] atchFileId;
    @SerializedName("bbs_id") private String boardType = "";
    @SerializedName("ntt_id") private String boardId = "";
    @SerializedName("ntcr_nm") private String userName = "";
    @SerializedName("ntcr_id") private String userId = "";
    @SerializedName("like") private String likeCount = "";
    @SerializedName("ntt_sj") private String boardTitle = "";
    @SerializedName("ntt_cn") private String boardContent = "";
    @SerializedName("frst_time") private String firstDate = "";
    @SerializedName("last_updt_time") private String lastUpdateDate = "";
    @SerializedName("rdcnt") private String readCount = "";
    @SerializedName("CntCOMMENT") private String commentCount = "0";
    @SerializedName("TN_FILEDETAIL") private BoardFileVO[] fileInfoArray;

    @Override
    public String toString() {
        return "userProfileUrl" + userProfileUrl;
    }

    public BoardVO(){

    }

    protected BoardVO(Parcel in) {
        userProfileUrl = in.readString();
        mainImageUrl = in.readString();
        mainImageWidth = in.readString();
        mainImageHeight = in.readString();
        atchFileId = in.createStringArray();
        boardType = in.readString();
        boardId = in.readString();
        userName = in.readString();
        userId = in.readString();
        likeCount = in.readString();
        boardTitle = in.readString();
        boardContent = in.readString();
        firstDate = in.readString();

        lastUpdateDate = in.readString();
        readCount = in.readString();
        commentCount = in.readString();
    }

    public static final Creator<BoardVO> CREATOR = new Creator<BoardVO>() {
        @Override
        public BoardVO createFromParcel(Parcel in) {
            return new BoardVO(in);
        }

        @Override
        public BoardVO[] newArray(int size) {
            return new BoardVO[size];
        }
    };

    public BoardFileVO[] getFileInfoArray() {
        return fileInfoArray;
    }

    public void setFileInfoArray(BoardFileVO[] fileInfoArray) {
        this.fileInfoArray = fileInfoArray;
    }

    public static Creator<BoardVO> getCREATOR() {
        return CREATOR;
    }

    @Bindable
    public String getMainImageWidth() {
        return mainImageWidth;
    }

    public void setMainImageWidth(String mainImageWidth) {
        this.mainImageWidth = mainImageWidth;
        notifyPropertyChanged(BR.mainImageWidth);
    }

    @Bindable
    public String getMainImageHeight() {
        return mainImageHeight;
    }

    public void setMainImageHeight(String mainImageHeight) {
        this.mainImageHeight = mainImageHeight;
        notifyPropertyChanged(BR.mainImageHeight);
    }

    public String[] getAtchFileId() {
        return atchFileId;
    }

    public void setAtchFileId(String[] atchFileId) {
        this.atchFileId = atchFileId;
    }

    @Bindable
    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
        notifyPropertyChanged(BR.userProfileUrl);
    }

    @Bindable
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {

        this.mainImageUrl = mainImageUrl;
        notifyPropertyChanged(BR.mainImageUrl);
    }

    @Bindable
    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
        notifyPropertyChanged(BR.boardType);
    }

    @Bindable
    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
        notifyPropertyChanged(BR.boardId);
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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userKey);
    }

    @Bindable
    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
        notifyPropertyChanged(BR.likeCount);
    }

    @Bindable
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
        notifyPropertyChanged(BR.boardTitle);
    }

    @Bindable
    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
        notifyPropertyChanged(BR.boardContent);
    }

    @Bindable
    public String getFirstDate() {

        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
        notifyPropertyChanged(BR.firstDate);
    }

    @Bindable
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        notifyPropertyChanged(BR.lastUpdateDate);
    }

    @Bindable
    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
        notifyPropertyChanged(BR.readCount);
    }

    @Bindable
    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
        notifyPropertyChanged(BR.commentCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userProfileUrl);
        parcel.writeString(mainImageUrl);
        parcel.writeString(mainImageWidth);
        parcel.writeString(mainImageHeight);
        parcel.writeStringArray(atchFileId);
        parcel.writeString(boardType);
        parcel.writeString(boardId);
        parcel.writeString(userName);
        parcel.writeString(userId);
        parcel.writeString(likeCount);
        parcel.writeString(boardTitle);
        parcel.writeString(boardContent);
        parcel.writeString(firstDate);
        parcel.writeString(lastUpdateDate);
        parcel.writeString(readCount);
        parcel.writeString(commentCount);
    }
}
