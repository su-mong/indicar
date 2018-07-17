package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.UserDao;
import com.iindicar.indicar.databinding.ActivityEditProfileBinding;
import com.iindicar.indicar.utils.ByteLengthFilter;
import com.iindicar.indicar.utils.LocaleHelper;
import com.loopj.android.http.RequestParams;

import io.fabric.sdk.android.Fabric;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding> {
    public static final int RESULT_UPDATED = 101; // Activity Result Code 유저이름 변경 된 경우

    public final int MAX_BYTE_OF_USER_NAME = 20; // 유저이름(닉네임) 최대 입력 수

    public final ObservableField<String> inputUserName = new ObservableField<>(); // 뷰(EditText)에 바인딩
    public final ObservableBoolean isLoading = new ObservableBoolean(false); // 프로그래스 바 show/hide

    private SharedPreferences prefLogin;
    private String userId;
    private String userName;

    private UserDao userDao = new UserDao();
    Resources resources;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        leftImageId.set(R.drawable.btn_back);
        centerImageId.set(R.drawable.logo_account);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        Context boardFilterContext = LocaleHelper.setLocale(getApplicationContext());
        resources = boardFilterContext.getResources();

        binding.setActivity(this);

        getUserPreference();
        initView();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    private void getUserPreference() {
        prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        userId = prefLogin.getString("id","");
        userName = prefLogin.getString("name","");

        binding.inputName.setText(userName);
        binding.inputName.setSelection(userName.length()); // 닉네임 맨 뒤로 커서 이동
        inputUserName.set(userName);
    }

    private void initView(){
        //언어에 맞게 레이아웃을 적용한다.
        binding.tvAName2.setText(resources.getString(R.string.editNameTitle));
        binding.inputName.setHint(resources.getString(R.string.editNameHint));
        binding.buttonSubmit.setText(resources.getString(R.string.editButton));

        // 액션바 뒤로가기 버튼
        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 글자 수 20byte 제한하는 필터를 EditText 에 적용시킨다
        InputFilter[] filters = new InputFilter[] {new ByteLengthFilter(MAX_BYTE_OF_USER_NAME, "KSC5601")};
        binding.inputName.setFilters(filters);
        binding.inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString();

                if(userName.equals(input)){ // 닉네임이 기존과 동일한 경우 버튼 비활성화
                    binding.buttonSubmit.setEnabled(false);
                    binding.buttonSubmit.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                } else { // 다른 경우 버튼 활성화
                    binding.buttonSubmit.setEnabled(true);
                    binding.buttonSubmit.setTextColor(getResources().getColor(R.color.colorLightGrey));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputUserName.set(editable.toString());
            }
        });
        binding.buttonSubmit.setEnabled(false);
        binding.buttonSubmit.setTextColor(getResources().getColor(R.color.cardview_dark_background));
    }

    public void onSubmitClicked(){

        isLoading.set(true);

        RequestParams params = new RequestParams();
        params.put("id", userId);
        params.put("name", inputUserName.get());
        userDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                isLoading.set(false);

                // 수정된 정보 sharedPreference 저장
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putString("name", inputUserName.get());
                editor.putLong("profileEditDate", System.currentTimeMillis());
                editor.apply();

                setResult(RESULT_UPDATED);
                finish();
            }

            @Override
            public void onDataNotAvailable() {
                isLoading.set(false);
                showSnackBar(getString(R.string.strNotAllowUpdateUser)) ;
            }
        });
    }
}