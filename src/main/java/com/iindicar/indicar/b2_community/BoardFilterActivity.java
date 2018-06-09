package com.iindicar.indicar.b2_community;

import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.iindicar.indicar.BaseActivity2;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityBoardFilterBinding;

public class BoardFilterActivity extends BaseActivity2<ActivityBoardFilterBinding> {
    public final static int REQUEST_CAR_FILTER = 11;
    public final ObservableField<String> searchInputText = new ObservableField<>();
    public final ObservableField<String> selectedCarFilter = new ObservableField<>();

    public final ObservableField<Boolean[]> isBoardFilterSelected = new ObservableField<>(new Boolean[]{false, false, false, false});

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_filter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.setActivity(this);
        initView();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    private void initView() {
        binding.buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
            }
        });

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
