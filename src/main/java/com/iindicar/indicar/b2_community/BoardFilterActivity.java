package com.iindicar.indicar.b2_community;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityBoardFilterBinding;

import java.util.List;

public class BoardFilterActivity extends BaseActivity<ActivityBoardFilterBinding> {
    public final static int REQUEST_CAR_FILTER = 11;
    public final ObservableField<String> searchInputText = new ObservableField<>();
    public final ObservableField<String> selectedCarFilter = new ObservableField<>();

    public final ObservableField<Boolean[]> isBoardFilterSelected = new ObservableField<>(new Boolean[]{false, false, false, false});

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_filter;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_community);
        leftImageId.set(R.drawable.btn_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.setActivity(this);
        initView();
    }

    private void initView() {
        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onCarSearchClicked(){
        Intent intent = new Intent(getApplicationContext(), CarFilterActivity.class);
        startActivityForResult(intent, REQUEST_CAR_FILTER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAR_FILTER) {
            if (resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra("car_name");
                selectedCarFilter.set(returnValue);
            }
        }
    }

    public void onBoardFilterSelected(int position){
        isBoardFilterSelected.get()[position] = !isBoardFilterSelected.get()[position];
    }
}
