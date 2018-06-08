package com.iindicar.indicar;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.iindicar.indicar.databinding.ActionBarLayoutBinding;
import com.iindicar.indicar.utils.CustomActionBar;

/**
 * Created by yeseul on 2018-04-13.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;
    protected ActionBarLayoutBinding actionBarBinding;

    public final ObservableInt centerImageId = new ObservableInt();
    public final ObservableInt leftImageId = new ObservableInt();

    protected InputMethodManager keyBoardManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());

        initActionBar();
        initKeyBoardManager();
    }

    protected abstract int getLayoutId();

    protected void initActionBar(){
        setActionBarImage(centerImageId, leftImageId);

        actionBarBinding = CustomActionBar.customize(this, getSupportActionBar());
        actionBarBinding.setActivity(this);
    }

    protected abstract void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId);

    public B getBinding(){
        return binding;
    }

    protected void initKeyBoardManager() {
        keyBoardManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    protected void showKeyBoard(View view){
        keyBoardManager.showSoftInput(view, 0);
    }

    protected void hideKeyBoard(View view){
        keyBoardManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showSnackBar(String message){
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    protected void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
