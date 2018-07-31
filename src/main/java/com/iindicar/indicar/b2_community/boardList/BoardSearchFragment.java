package com.iindicar.indicar.b2_community.boardList;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.iindicar.indicar.BaseActivity2;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.CarFilterActivity;
import com.iindicar.indicar.databinding.ActivityBoardFilterBinding;

import static android.app.Activity.RESULT_OK;

public class BoardSearchFragment extends BaseFragment<ActivityBoardFilterBinding> {
    public final static int REQUEST_CAR_FILTER = 11;
    public final ObservableField<String> searchInputText = new ObservableField<>();
    public final ObservableField<String> selectedCarFilter = new ObservableField<>();

    public final ObservableField<Boolean[]> isBoardFilterSelected = new ObservableField<>(new Boolean[]{false, false, false, false});

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_filter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setFragment(this);
        initView();
    }

    private void initView() {
/*
        binding.searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent();
                    intent.putExtra("searchKey", binding.searchText.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                    overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                    return true;
                }
                return false;
            }
        });*/
    }

    public void onCarSearchClicked(){
        Intent intent = new Intent(getContext(), CarFilterActivity.class);
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
