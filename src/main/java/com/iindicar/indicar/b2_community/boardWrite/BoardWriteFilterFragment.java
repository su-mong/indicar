package com.iindicar.indicar.b2_community.boardWrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.CarFilterActivity;
import com.iindicar.indicar.databinding.BoardWriteFilterFragmentBinding;

/**
 * Created by yeseul on 2018-05-06.
 */

public class BoardWriteFilterFragment extends BaseFragment<BoardWriteFilterFragmentBinding> {

    private BoardWriteEditViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.board_write_filter_fragment;
    }

    public void setViewModel(BoardWriteEditViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setViewModel(viewModel);
        binding.buttonSearchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CarFilterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == 13) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("car_name");
                }

    }
}



