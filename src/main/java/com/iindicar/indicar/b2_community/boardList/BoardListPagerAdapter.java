package com.iindicar.indicar.b2_community.boardList;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

import com.iindicar.indicar.BaseViewPagerAdapter;

import static com.iindicar.indicar.b2_community.boardList.BoardListAdapter.BOARD_ALL;
import static com.iindicar.indicar.b2_community.boardList.BoardListAdapter.BOARD_POPULAR;


/**
 * Created by yeseul on 2018-04-16.
 */

public class BoardListPagerAdapter extends BaseViewPagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private final int TAB_COUNT = 2;

    public BoardListPagerAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        switch (position){
            case BOARD_POPULAR:
                fragment = new BoardListFragment();
                args.putInt("boardTab", BOARD_POPULAR);
                fragment.setArguments(args);
                break;
            case BOARD_ALL:
                fragment = new BoardListFragment();
                args.putInt("boardTab", BOARD_ALL);
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
