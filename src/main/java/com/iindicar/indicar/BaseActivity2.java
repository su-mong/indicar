package com.iindicar.indicar;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iindicar.indicar.databinding.ActionBarLayoutBinding;
import com.iindicar.indicar.utils.CustomActionBar;

/**
 * Created by yeseul on 2018-04-13.
 */

public abstract class BaseActivity2<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;

    public final ObservableInt centerImageId = new ObservableInt();
    public final ObservableInt leftImageId = new ObservableInt();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        ((Activity)this).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    protected abstract int getLayoutId();




    public B getBinding(){
        return binding;
    }
}
