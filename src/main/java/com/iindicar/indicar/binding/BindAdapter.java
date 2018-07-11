package com.iindicar.indicar.binding;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageButton;

import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.CommunityFragment;
import com.iindicar.indicar.b2_community.boardList.BoardListPagerAdapter;
import com.iindicar.indicar.utils.LocaleHelper;

/**
 * Created by yeseul on 2018-04-13.
 */

public class BindAdapter {

    public static final int NUM_OF_BOARD_BUTTONS = 2; // 게시판 탭 버튼 개수

    @BindingAdapter(value = {"fragment", "tabLayout"}, requireAll = false)
    public static void bindBoardTab(ViewPager viewPager,
                                   CommunityFragment fragment,
                                   TabLayout tabLayout){

        Context bindAdapterContext = LocaleHelper.setLocale(fragment.getActivity());
        Resources resources = bindAdapterContext.getResources();

        viewPager.setAdapter(new BoardListPagerAdapter(viewPager.getContext(), fragment.getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        String[] TAB_NAME = {
                resources.getString(R.string.cmTab1),
                resources.getString(R.string.cmTab2)
        };

        for(int i = 0 ; i < NUM_OF_BOARD_BUTTONS ; i++){
            tabLayout.getTabAt(i).setText(TAB_NAME[i]);
        }

    }

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
    public static void setSelected(ImageButton imageView, Boolean bool){
        imageView.setSelected(bool);
    }

}
