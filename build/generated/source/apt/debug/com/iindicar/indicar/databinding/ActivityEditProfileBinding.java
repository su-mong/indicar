package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityEditProfileBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tvA_name2, 4);
    }
    // views
    @NonNull
    public final android.widget.Button buttonSubmit;
    @NonNull
    public final android.widget.EditText inputName;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    public final android.widget.ProgressBar pbALoading;
    @NonNull
    public final android.widget.TextView tvAName2;
    // variables
    @Nullable
    private com.iindicar.indicar.b4_account.EditProfileActivity mActivity;
    @Nullable
    private final android.view.View.OnClickListener mCallback26;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityEditProfileBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.buttonSubmit = (android.widget.Button) bindings[2];
        this.buttonSubmit.setTag(null);
        this.inputName = (android.widget.EditText) bindings[1];
        this.inputName.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pbALoading = (android.widget.ProgressBar) bindings[3];
        this.pbALoading.setTag(null);
        this.tvAName2 = (android.widget.TextView) bindings[4];
        setRootTag(root);
        // listeners
        mCallback26 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
            setActivity((com.iindicar.indicar.b4_account.EditProfileActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.b4_account.EditProfileActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b4_account.EditProfileActivity getActivity() {
        return mActivity;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityIsLoading((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeActivityInputUserName((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityIsLoading(android.databinding.ObservableBoolean ActivityIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityInputUserName(android.databinding.ObservableField<java.lang.String> ActivityInputUserName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        com.iindicar.indicar.b4_account.EditProfileActivity activity = mActivity;
        java.lang.String activityInputUserNameGet = null;
        android.databinding.ObservableBoolean activityIsLoading = null;
        android.databinding.ObservableField<java.lang.String> activityInputUserName = null;
        boolean activityIsLoadingGet = false;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (activity != null) {
                        // read activity.isLoading
                        activityIsLoading = activity.isLoading;
                    }
                    updateRegistration(0, activityIsLoading);


                    if (activityIsLoading != null) {
                        // read activity.isLoading.get()
                        activityIsLoadingGet = activityIsLoading.get();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (activity != null) {
                        // read activity.inputUserName
                        activityInputUserName = activity.inputUserName;
                    }
                    updateRegistration(1, activityInputUserName);


                    if (activityInputUserName != null) {
                        // read activity.inputUserName.get()
                        activityInputUserNameGet = activityInputUserName.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.buttonSubmit.setOnClickListener(mCallback26);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.inputName, activityInputUserNameGet);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.pbALoading.setVisibility(com.iindicar.indicar.binding.BindConversion.convertBooleanToVisibility(activityIsLoadingGet));
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // activity != null
        boolean activityJavaLangObjectNull = false;
        // activity
        com.iindicar.indicar.b4_account.EditProfileActivity activity = mActivity;



        activityJavaLangObjectNull = (activity) != (null);
        if (activityJavaLangObjectNull) {


            activity.onSubmitClicked();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityEditProfileBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_edit_profile, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.activity_edit_profile, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityEditProfileBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_edit_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityEditProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity.isLoading
        flag 1 (0x2L): activity.inputUserName
        flag 2 (0x3L): activity
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}