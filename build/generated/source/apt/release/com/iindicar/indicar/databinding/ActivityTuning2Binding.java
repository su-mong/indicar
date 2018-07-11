package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTuning2Binding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tab_logo, 4);
        sViewsWithIds.put(R.id.text_empty, 5);
    }
    // views
    @NonNull
    public final android.widget.GridView gridViewCarList;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.ProgressBar mboundView3;
    @NonNull
    public final android.widget.EditText searchText;
    @NonNull
    public final android.support.design.widget.TabLayout tabLogo;
    @NonNull
    public final android.widget.TextView textEmpty;
    // variables
    @Nullable
    private com.iindicar.indicar.b1_tunning.Tuning2Activity mActivity;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTuning2Binding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 3);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.gridViewCarList = (android.widget.GridView) bindings[2];
        this.gridViewCarList.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (android.widget.ProgressBar) bindings[3];
        this.mboundView3.setTag(null);
        this.searchText = (android.widget.EditText) bindings[1];
        this.searchText.setTag(null);
        this.tabLogo = (android.support.design.widget.TabLayout) bindings[4];
        this.textEmpty = (android.widget.TextView) bindings[5];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
            setActivity((com.iindicar.indicar.b1_tunning.Tuning2Activity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.b1_tunning.Tuning2Activity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b1_tunning.Tuning2Activity getActivity() {
        return mActivity;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityIsLoading((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeActivitySelectedCarList((android.databinding.ObservableList<com.iindicar.indicar.data.vo.CarDataVO>) object, fieldId);
            case 2 :
                return onChangeActivitySearchInput((android.databinding.ObservableField<java.lang.String>) object, fieldId);
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
    private boolean onChangeActivitySelectedCarList(android.databinding.ObservableList<com.iindicar.indicar.data.vo.CarDataVO> ActivitySelectedCarList, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivitySearchInput(android.databinding.ObservableField<java.lang.String> ActivitySearchInput, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        com.iindicar.indicar.b1_tunning.Tuning2Activity activity = mActivity;
        android.databinding.ObservableBoolean activityIsLoading = null;
        java.lang.String activitySearchInputGet = null;
        android.databinding.ObservableList<com.iindicar.indicar.data.vo.CarDataVO> activitySelectedCarList = null;
        boolean activityIsLoadingGet = false;
        android.databinding.ObservableField<java.lang.String> activitySearchInput = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

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
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (activity != null) {
                        // read activity.selectedCarList
                        activitySelectedCarList = activity.selectedCarList;
                    }
                    updateRegistration(1, activitySelectedCarList);
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (activity != null) {
                        // read activity.searchInput
                        activitySearchInput = activity.searchInput;
                    }
                    updateRegistration(2, activitySearchInput);


                    if (activitySearchInput != null) {
                        // read activity.searchInput.get()
                        activitySearchInputGet = activitySearchInput.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            com.iindicar.indicar.b1_tunning.Tuning2Activity.bindItemList(this.gridViewCarList, activitySelectedCarList);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.mboundView3.setVisibility(com.iindicar.indicar.binding.BindConversion.convertBooleanToVisibility(activityIsLoadingGet));
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.searchText, activitySearchInputGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityTuning2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTuning2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityTuning2Binding>inflate(inflater, com.iindicar.indicar.R.layout.activity_tuning2, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityTuning2Binding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTuning2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.activity_tuning2, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityTuning2Binding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTuning2Binding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_tuning2_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityTuning2Binding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity.isLoading
        flag 1 (0x2L): activity.selectedCarList
        flag 2 (0x3L): activity.searchInput
        flag 3 (0x4L): activity
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}