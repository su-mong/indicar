package com.iindicar.indicar.utils;

import android.net.Uri;

import java.util.List;

/**
 * Created by yeseul on 2018-04-14.
 */

public interface IPickPhotoHelper<T extends Uri> {

    void pickFromCamera(loadPhotoCallBack callBack);

    void pickFromAlbum(int maxSelectable, loadPhotoListCallBack callBack);

    interface loadPhotoCallBack<T> {

        void onPhotoLoaded(Uri photoUri, String imagePath);

        void onPhotoLoaded(T photoUri);

        void onPhotoNotAvailable();
    }

    interface loadPhotoListCallBack<T> {

        void onPhotoListLoaded(List<T> photoUriList);

        void onPhotoNotAvailable();
    }
}
