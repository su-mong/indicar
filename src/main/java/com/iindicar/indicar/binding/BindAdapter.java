package com.iindicar.indicar.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.iindicar.indicar.b2_community.CommunityFragment;
import com.iindicar.indicar.b2_community.boardList.BoardListPagerAdapter;

/**
 * Created by yeseul on 2018-04-13.
 */

public class BindAdapter {


    @BindingAdapter({"onTouch"})
    public static void bindOnTouch(ImageButton imageButton,
                               View.OnTouchListener onTouchListener){

        imageButton.setOnTouchListener(onTouchListener);
    }

    @BindingAdapter({"onRefresh"})
    public static void bindOnRefresh(SwipeRefreshLayout refreshLayout,
                             SwipeRefreshLayout.OnRefreshListener onRefreshListener){

        refreshLayout.setOnRefreshListener(onRefreshListener);
    }

    @BindingAdapter({"setSelected"})
    public static void setSelected(View imageView, Boolean bool){
        imageView.setSelected(bool);
    }

}
