package com.iindicar.indicar.b2_community;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.commit451.teleprinter.Teleprinter;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteActivity;
import com.iindicar.indicar.databinding.CommunityFragmentBinding;

import java.util.Observable;

import static android.view.inputmethod.InputMethodManager.SHOW_FORCED;
import static com.iindicar.indicar.Constant.BoardWrite.NEW;

public class CommunityFragment extends BaseFragment<CommunityFragmentBinding> {

    public final ObservableField<String> textSearch = new ObservableField<>();
    public final ObservableBoolean isSearchBarOpen = new ObservableBoolean(false);
    public final ObservableBoolean showButton = new ObservableBoolean(true);

    private Teleprinter keyboard;

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

        binding.setFragment(this);

        binding.viewPagerBoard.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabBoardType));

        binding.tabBoardType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                binding.viewPagerBoard.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        keyboard = new Teleprinter((Activity) context);

    }

    public void showKeyboard(){
        keyboard.showKeyboard(binding.textSearch);
        binding.textSearch.requestFocus();
    }

    public void hideKeyboard(){
        keyboard.hideKeyboard();
        binding.textSearch.clearFocus();
    }

    /**
     * 검색창 열기, 닫기
     * */
    public void toggleSearchBar() {

        if(!isSearchBarOpen.get()){ // 검색창 open 애니메이션

            openSearchBar();

        } else { // 검색창 close 애니메이션

            closeSearchBar();

        }
    }

    public void openSearchBar(){

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0
                );
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                isSearchBarOpen.set(true);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                showKeyboard();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.searchBarLayout.startAnimation(animation);

    }

    public void closeSearchBar() {

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1
        );
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                hideKeyboard();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                isSearchBarOpen.set(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.searchBarLayout.startAnimation(animation);

    }

}

