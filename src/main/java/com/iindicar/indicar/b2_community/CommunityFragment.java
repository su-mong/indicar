package com.iindicar.indicar.b2_community;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.CommunityFragmentBinding;

import io.fabric.sdk.android.Fabric;

public class CommunityFragment extends BaseFragment<CommunityFragmentBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.community_fragment;
    }

    public CommunityFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(getActivity(),new Crashlytics());
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
    }
}

