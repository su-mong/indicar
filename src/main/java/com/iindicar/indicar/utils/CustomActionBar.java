package com.iindicar.indicar.utils;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActionBarLayoutBinding;

/**
 * Created by yeseul on 2018-03-23.
 */

public class CustomActionBar {

    public static ActionBarLayoutBinding customize(Context context, ActionBar actionBar){

        View view = LayoutInflater.from(context).inflate(R.layout.action_bar_layout, null);
        ActionBarLayoutBinding binding = DataBindingUtil.bind(view);

        ((Activity)context).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);     //액션바 아이콘을 업 네비게이션 형태로 표시
        actionBar.setDisplayShowTitleEnabled(false);    //액션바에 표시되는 제목의 표시유무 설정
        actionBar.setDisplayShowHomeEnabled(false);     //홈 아이콘 숨김처리

        actionBar.setCustomView(view); //커스텀 뷰 적용
        actionBar.setElevation(2);

        // 액션바 padding 없애기
        Toolbar parent = (Toolbar)view.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return binding;
    }
}



