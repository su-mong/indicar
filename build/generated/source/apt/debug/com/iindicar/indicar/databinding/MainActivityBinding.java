package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MainActivityBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.view_pager_main, 5);
    }
    // views
    @NonNull
    public final android.widget.ImageButton btnMAccount;
    @NonNull
    public final android.widget.ImageButton btnMCommunity;
    @NonNull
    public final android.widget.ImageButton btnMShopping;
    @NonNull
    public final android.widget.ImageButton btnMTuning;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.FrameLayout viewPagerMain;
    // variables
    @Nullable
    private com.iindicar.indicar.a1_main.MainActivity mActivity;
    @Nullable
    private com.iindicar.indicar.Constant mConstant;
    @Nullable
    private final android.view.View.OnClickListener mCallback39;
    @Nullable
    private final android.view.View.OnClickListener mCallback37;
    @Nullable
    private final android.view.View.OnClickListener mCallback38;
    @Nullable
    private final android.view.View.OnClickListener mCallback36;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MainActivityBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 5);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnMAccount = (android.widget.ImageButton) bindings[4];
        this.btnMAccount.setTag(null);
        this.btnMCommunity = (android.widget.ImageButton) bindings[2];
        this.btnMCommunity.setTag(null);
        this.btnMShopping = (android.widget.ImageButton) bindings[3];
        this.btnMShopping.setTag(null);
        this.btnMTuning = (android.widget.ImageButton) bindings[1];
        this.btnMTuning.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.viewPagerMain = (android.widget.FrameLayout) bindings[5];
        setRootTag(root);
        // listeners
        mCallback39 = new android.databinding.generated.callback.OnClickListener(this, 4);
        mCallback37 = new android.databinding.generated.callback.OnClickListener(this, 2);
        mCallback38 = new android.databinding.generated.callback.OnClickListener(this, 3);
        mCallback36 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.a1_main.MainActivity) variable);
        }
        else if (BR.constant == variableId) {
            setConstant((com.iindicar.indicar.Constant) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.a1_main.MainActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.a1_main.MainActivity getActivity() {
        return mActivity;
    }
    public void setConstant(@Nullable com.iindicar.indicar.Constant Constant) {
        this.mConstant = Constant;
    }
    @Nullable
    public com.iindicar.indicar.Constant getConstant() {
        return mConstant;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeConstantSHOPPING((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeActivityCurrentTab((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeConstantCOMMUNITY((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeConstantTUNING((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeConstantACCOUNT((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeConstantSHOPPING(android.databinding.ObservableField<java.lang.String> ConstantSHOPPING, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityCurrentTab(android.databinding.ObservableField<java.lang.String> ActivityCurrentTab, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConstantCOMMUNITY(android.databinding.ObservableField<java.lang.String> ConstantCOMMUNITY, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConstantTUNING(android.databinding.ObservableField<java.lang.String> ConstantTUNING, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConstantACCOUNT(android.databinding.ObservableField<java.lang.String> ConstantACCOUNT, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.databinding.ObservableField<java.lang.String> activityCurrentTab = null;
        boolean activityCurrentTabConstantTUNING = false;
        com.iindicar.indicar.a1_main.MainActivity activity = mActivity;
        boolean activityCurrentTabConstantACCOUNT = false;
        java.lang.String activityCurrentTabGet = null;
        boolean activityCurrentTabConstantCOMMUNITY = false;
        boolean activityCurrentTabConstantSHOPPING = false;

        if ((dirtyFlags & 0xa2L) != 0) {



                if (activity != null) {
                    // read activity.currentTab
                    activityCurrentTab = activity.currentTab;
                }
                updateRegistration(1, activityCurrentTab);


                if (activityCurrentTab != null) {
                    // read activity.currentTab.get()
                    activityCurrentTabGet = activityCurrentTab.get();
                }


                // read activity.currentTab.get() == Constant.TUNING.get()
                activityCurrentTabConstantTUNING = (activityCurrentTabGet) == (com.iindicar.indicar.Constant.TUNING.get());
                // read activity.currentTab.get() == Constant.ACCOUNT.get()
                activityCurrentTabConstantACCOUNT = (activityCurrentTabGet) == (com.iindicar.indicar.Constant.ACCOUNT.get());
                // read activity.currentTab.get() == Constant.COMMUNITY.get()
                activityCurrentTabConstantCOMMUNITY = (activityCurrentTabGet) == (com.iindicar.indicar.Constant.COMMUNITY.get());
                // read activity.currentTab.get() == Constant.SHOPPING.get()
                activityCurrentTabConstantSHOPPING = (activityCurrentTabGet) == (com.iindicar.indicar.Constant.SHOPPING.get());
        }
        // batch finished
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            this.btnMAccount.setTag(com.iindicar.indicar.Constant.ACCOUNT.get());
            this.btnMAccount.setOnClickListener(mCallback39);
            this.btnMCommunity.setTag(com.iindicar.indicar.Constant.COMMUNITY.get());
            this.btnMCommunity.setOnClickListener(mCallback37);
            this.btnMShopping.setTag(com.iindicar.indicar.Constant.SHOPPING.get());
            this.btnMShopping.setOnClickListener(mCallback38);
            this.btnMTuning.setTag(com.iindicar.indicar.Constant.TUNING.get());
            this.btnMTuning.setOnClickListener(mCallback36);
        }
        if ((dirtyFlags & 0xa2L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BindAdapter.setSelected(this.btnMAccount, activityCurrentTabConstantACCOUNT);
            com.iindicar.indicar.binding.BindAdapter.setSelected(this.btnMCommunity, activityCurrentTabConstantCOMMUNITY);
            com.iindicar.indicar.binding.BindAdapter.setSelected(this.btnMShopping, activityCurrentTabConstantSHOPPING);
            com.iindicar.indicar.binding.BindAdapter.setSelected(this.btnMTuning, activityCurrentTabConstantTUNING);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 4: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.a1_main.MainActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.setTab(callbackArg_0);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.a1_main.MainActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.setTab(callbackArg_0);
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.a1_main.MainActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.setTab(callbackArg_0);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.a1_main.MainActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.setTab(callbackArg_0);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static MainActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<MainActivityBinding>inflate(inflater, com.iindicar.indicar.R.layout.main_activity, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static MainActivityBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.main_activity, null, false), bindingComponent);
    }
    @NonNull
    public static MainActivityBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainActivityBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/main_activity_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new MainActivityBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): Constant.SHOPPING
        flag 1 (0x2L): activity.currentTab
        flag 2 (0x3L): Constant.COMMUNITY
        flag 3 (0x4L): Constant.TUNING
        flag 4 (0x5L): Constant.ACCOUNT
        flag 5 (0x6L): activity
        flag 6 (0x7L): constant
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}