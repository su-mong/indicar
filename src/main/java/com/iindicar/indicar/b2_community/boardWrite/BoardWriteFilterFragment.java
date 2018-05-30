package com.iindicar.indicar.b2_community.boardWrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.CarFilterActivity;
import com.iindicar.indicar.databinding.BoardWriteFilterFragmentBinding;

import static android.app.Activity.RESULT_OK;

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
                startActivityForResult(intent, 13);
            }
        });
        binding.buttonMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "준비중입니다.", Toast.LENGTH_SHORT).show();

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
                binding.textCarName.setText("선택된 차량 : " + returnValue);
            }
        }
    }

}



