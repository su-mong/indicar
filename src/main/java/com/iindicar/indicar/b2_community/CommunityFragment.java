package com.iindicar.indicar.b2_community;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.commit451.teleprinter.Teleprinter;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardList.BoardListPagerAdapter;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditActivity;
import com.iindicar.indicar.databinding.CommunityFragmentBinding;

public class CommunityFragment extends BaseFragment<CommunityFragmentBinding> {

    private final int NUM_OF_BOARD_BUTTONS = 3; // 게시판 탭 버튼 개수
    private final int tabImageIds[] = { // 탭 버튼 아이콘
            R.drawable.tab_hot,
            R.drawable.tab_all,
            R.drawable.tab_search
    };

    private BoardListPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.community_fragment;
    }

    public CommunityFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initTab();
        initWriteButton();
    }

    private void initWriteButton() {
        binding.btnWrite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent intent = new Intent(getContext(), BoardWriteEditActivity.class);
                    startActivityForResult(intent, 13);
                }
                return true;
            }
        });
    }

    private void initTab(){
        adapter = new BoardListPagerAdapter(context, getChildFragmentManager());
        binding.viewPagerBoard.setAdapter(adapter);
        binding.viewPagerBoard.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabBoardType));

        binding.tabBoardType.setupWithViewPager(binding.viewPagerBoard);
        for(int i = 0 ; i < NUM_OF_BOARD_BUTTONS ; i++){
            View view = View.inflate(context, R.layout.main_tab_layout, null);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(tabImageIds[i]);
            binding.tabBoardType.getTabAt(i).setCustomView(view);
        }
    }
}

