package com.iindicar.indicar.b2_community.boardWrite;

import android.content.Intent;

import com.iindicar.indicar.data.vo.WriteFileVO;

import java.util.List;

/**
 * Created by yeseul on 2018-04-25.
 */

public interface BoardWriteNavigator {

    Intent getActivityIntent();

    void onPageChangedToDetail();

    void onPageChangedToIntro();

    void onCancelWrite();

    void onSubmitWrite();

    void onFinishActivity();

    void showTestToast(String message);

    List<WriteFileVO> getWriteItems();

}
