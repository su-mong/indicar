package com.iindicar.indicar.b2_community.boardWrite;

/**
 * Created by yeseul on 2018-05-06.
 */

public interface BoardWriteEditNavigator {

    void changeToWriteItem();

    void changeToWriteFilter();

    void onBoardUpdated();

    void onBoardUploaded();

    void onCancelWrite();

    void onActivityFinish();

    void showSnackBar(String text);

    void pbOn();

    void pbOff();

}
