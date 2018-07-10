package com.iindicar.indicar.b2_community.boardWrite;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.CarFilterActivity;
import com.iindicar.indicar.databinding.BoardWriteFilterFragmentBinding;
import com.iindicar.indicar.utils.LocaleHelper;

import io.fabric.sdk.android.Fabric;

import static android.app.Activity.RESULT_OK;

/**
 * Created by yeseul on 2018-05-06.
 */

public class BoardWriteFilterFragment extends BaseFragment<BoardWriteFilterFragmentBinding> {

    private BoardWriteEditViewModel viewModel;

    Resources resources;

    @Override
    protected int getLayoutId() {
        return R.layout.board_write_filter_fragment;
    }

    public void setViewModel(BoardWriteEditViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(getActivity(),new Crashlytics());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Context boardWriteFilterContext = LocaleHelper.setLocale(getActivity());
        resources = boardWriteFilterContext.getResources();

        //언어에 맞는 뷰 셋팅
        binding.imageviewBWFTitle.setImageDrawable(resources.getDrawable(R.drawable.background_write_main));
        binding.textTypeofvehicle.setImageDrawable(resources.getDrawable(R.drawable.text_write_category_car));
        binding.textTypeofpost.setImageDrawable(resources.getDrawable(R.drawable.text_write_category_board));
        binding.buttonSearchCar.setImageDrawable(resources.getDrawable(R.drawable.img_search_bar));
        binding.textCarName.setText(resources.getString(R.string.selectNoCar));
        binding.buttonDayLife.setImageDrawable(resources.getDrawable(R.drawable.write_filter_daylife_selector));
        binding.buttonMarket.setImageDrawable(resources.getDrawable(R.drawable.write_filter_market_selector));
        binding.buttonDiy.setImageDrawable(resources.getDrawable(R.drawable.write_filter_diy_selector));

        binding.setViewModel(viewModel);
        binding.buttonSearchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CarFilterActivity.class);
                startActivityForResult(intent, 13);
            }
        });
        binding.buttonMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(binding.getRoot(), resources.getString(R.string.strNotPrepare), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra("car_name");
                viewModel.setCarName(returnValue);
                binding.textCarName.setText(resources.getString(R.string.selectCar) + returnValue);
            }
        }
    }

}



