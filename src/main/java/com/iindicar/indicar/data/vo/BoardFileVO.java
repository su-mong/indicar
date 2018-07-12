package com.iindicar.indicar.data.vo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.iindicar.indicar.BR;

/**
 * Created by yeseul on 2018-04-14.
 */

public class BoardFileVO extends BaseObservable {

    @SerializedName("atch_file_id") private String atchFileId = "";
    @SerializedName("file_sn") private int fileIndex;
    @SerializedName("file_stre_cours") private String fileUrl = "";
    @SerializedName("stre_file_nm") private String storeFileName = "";
    @SerializedName("orignl_file_nm") private String originalFileName = "";
    @SerializedName("file_extsn") private String fileExtension = "";
    @SerializedName("file_cn") private String fileContent = "";
    @SerializedName("img_width") private String fileWidth;
    @SerializedName("img_height") private String fileHeight;
    @SerializedName("file_size") private int fileSize;

    public BoardFileVO(){

    }

    @Bindable
    public String getFileContent() {
        if(fileContent==null)
            fileContent="";

        return fileContent;
    }

    public void setFileContent(String fileContent) {
        if(fileContent!=null)
        this.fileContent = fileContent;
        notifyPropertyChanged(BR.fileContent);
    }

    @Bindable
    public String getFileWidth() {
        return fileWidth;
    }

    public void setFileWidth(String fileWidth) {
        this.fileWidth = fileWidth;
        notifyPropertyChanged(BR.fileWidth);
        Log.d("BoardFileVO", "imageWidth" + getFileWidth());
    }

    @Bindable
    public String getFileHeight() {
        return fileHeight;
    }

    public void setFileHeight(String fileHeight) {
        this.fileHeight = fileHeight;
        notifyPropertyChanged(BR.fileHeight);
        Log.d("BoardFileVO", "imageHeight" + getFileHeight());
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Bindable
    public String getAtchFileId() {
        return atchFileId;
    }

    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
        notifyPropertyChanged(BR.atchFileId);
    }

    @Bindable
    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
        notifyPropertyChanged(BR.fileIndex);
    }

    @Bindable
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        notifyPropertyChanged(BR.fileUrl);
    }

    @Bindable
    public String getStoreFileName() {
        return storeFileName;
    }

    public void setStoreFileName(String storeFileName) {
        this.storeFileName = storeFileName;
        notifyPropertyChanged(BR.storeFileName);
    }

    @Bindable
    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        notifyPropertyChanged(BR.originalFileName);
    }

    @Bindable
    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        notifyPropertyChanged(BR.fileExtension);
    }

}
