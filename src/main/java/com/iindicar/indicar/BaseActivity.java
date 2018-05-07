package com.iindicar.indicar;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.iindicar.indicar.databinding.ActionBarLayoutBinding;
import com.iindicar.indicar.utils.CustomActionBar;

import java.util.Observer;

/**
 * Created by yeseul on 2018-04-13.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;
    protected ActionBarLayoutBinding actionBarBinding;

    public final ObservableInt centerImageId = new ObservableInt();
    public final ObservableInt leftImageId = new ObservableInt();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());

        initActionBar();
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
}
