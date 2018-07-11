package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBoardFilterBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.button_back, 10);
        sViewsWithIds.put(R.id.text_posttype, 11);
        sViewsWithIds.put(R.id.text_cartype, 12);
        sViewsWithIds.put(R.id.text_selected_car, 13);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonBack;
    @NonNull
    public final android.widget.Button buttonFilterAll;
    @NonNull
    public final android.widget.Button buttonFilterDiy;
    @NonNull
    public final android.widget.Button buttonFilterFree;
    @NonNull
    public final android.widget.Button buttonFilterSell;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.FrameLayout mboundView6;
    @NonNull
    private final android.widget.ImageView mboundView8;
    @NonNull
    private final android.widget.TextView mboundView9;
    @NonNull
    public final android.widget.EditText searchText;
    @NonNull
    public final android.widget.TextView textCarbrandHint;
    @NonNull
    public final android.widget.TextView textCartype;
    @NonNull
    public final android.widget.TextView textPosttype;
    @NonNull
    public final android.widget.TextView textSelectedCar;
    // variables
    @Nullable
    private com.iindicar.indicar.b2_community.BoardFilterActivity mActivity;
    @Nullable
    private final android.view.View.OnClickListener mCallback15;
    @Nullable
    private final android.view.View.OnClickListener mCallback16;
    @Nullable
    private final android.view.View.OnClickListener mCallback13;
    @Nullable
    private final android.view.View.OnClickListener mCallback14;
    @Nullable
    private final android.view.View.OnClickListener mCallback19;
    @Nullable
    private final android.view.View.OnClickListener mCallback17;
    @Nullable
    private final android.view.View.OnClickListener mCallback18;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityBoardFilterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 3);
        final Object[] bindings = mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.buttonBack = (android.widget.ImageButton) bindings[10];
        this.buttonFilterAll = (android.widget.Button) bindings[2];
        this.buttonFilterAll.setTag(null);
        this.buttonFilterDiy = (android.widget.Button) bindings[5];
        this.buttonFilterDiy.setTag(null);
        this.buttonFilterFree = (android.widget.Button) bindings[3];
        this.buttonFilterFree.setTag(null);
        this.buttonFilterSell = (android.widget.Button) bindings[4];
        this.buttonFilterSell.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView6 = (android.widget.FrameLayout) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView8 = (android.widget.ImageView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (android.widget.TextView) bindings[9];
        this.mboundView9.setTag(null);
        this.searchText = (android.widget.EditText) bindings[1];
        this.searchText.setTag(null);
        this.textCarbrandHint = (android.widget.TextView) bindings[7];
        this.textCarbrandHint.setTag(null);
        this.textCartype = (android.widget.TextView) bindings[12];
        this.textPosttype = (android.widget.TextView) bindings[11];
        this.textSelectedCar = (android.widget.TextView) bindings[13];
        setRootTag(root);
        // listeners
        mCallback15 = new android.databinding.generated.callback.OnClickListener(this, 3);
        mCallback16 = new android.databinding.generated.callback.OnClickListener(this, 4);
        mCallback13 = new android.databinding.generated.callback.OnClickListener(this, 1);
        mCallback14 = new android.databinding.generated.callback.OnClickListener(this, 2);
        mCallback19 = new android.databinding.generated.callback.OnClickListener(this, 7);
        mCallback17 = new android.databinding.generated.callback.OnClickListener(this, 5);
        mCallback18 = new android.databinding.generated.callback.OnClickListener(this, 6);
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
            setActivity((com.iindicar.indicar.b2_community.BoardFilterActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.b2_community.BoardFilterActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b2_community.BoardFilterActivity getActivity() {
        return mActivity;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivitySearchInputText((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeActivityIsBoardFilterSelected((android.databinding.ObservableField<java.lang.Boolean[]>) object, fieldId);
            case 2 :
                return onChangeActivitySelectedCarFilter((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivitySearchInputText(android.databinding.ObservableField<java.lang.String> ActivitySearchInputText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityIsBoardFilterSelected(android.databinding.ObservableField<java.lang.Boolean[]> ActivityIsBoardFilterSelected, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivitySelectedCarFilter(android.databinding.ObservableField<java.lang.String> ActivitySelectedCarFilter, int fieldId) {
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
        boolean androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected2 = false;
        java.lang.Boolean activityIsBoardFilterSelected1 = null;
        android.databinding.ObservableField<java.lang.String> activitySearchInputText = null;
        java.lang.String activitySelectedCarFilterGet = null;
        com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;
        boolean androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected3 = false;
        java.lang.Boolean activityIsBoardFilterSelected3 = null;
        android.databinding.ObservableField<java.lang.Boolean[]> activityIsBoardFilterSelected = null;
        java.lang.Boolean[] activityIsBoardFilterSelectedGet = null;
        boolean androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected1 = false;
        java.lang.Boolean activityIsBoardFilterSelected0 = null;
        boolean androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected0 = false;
        java.lang.String activitySearchInputTextGet = null;
        android.databinding.ObservableField<java.lang.String> activitySelectedCarFilter = null;
        java.lang.Boolean activityIsBoardFilterSelected2 = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (activity != null) {
                        // read activity.searchInputText
                        activitySearchInputText = activity.searchInputText;
                    }
                    updateRegistration(0, activitySearchInputText);


                    if (activitySearchInputText != null) {
                        // read activity.searchInputText.get()
                        activitySearchInputTextGet = activitySearchInputText.get();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (activity != null) {
                        // read activity.isBoardFilterSelected
                        activityIsBoardFilterSelected = activity.isBoardFilterSelected;
                    }
                    updateRegistration(1, activityIsBoardFilterSelected);


                    if (activityIsBoardFilterSelected != null) {
                        // read activity.isBoardFilterSelected.get()
                        activityIsBoardFilterSelectedGet = activityIsBoardFilterSelected.get();
                    }


                    if (activityIsBoardFilterSelectedGet != null) {
                        // read activity.isBoardFilterSelected.get()[1]
                        activityIsBoardFilterSelected1 = getFromArray(activityIsBoardFilterSelectedGet, 1);
                        // read activity.isBoardFilterSelected.get()[3]
                        activityIsBoardFilterSelected3 = getFromArray(activityIsBoardFilterSelectedGet, 3);
                        // read activity.isBoardFilterSelected.get()[0]
                        activityIsBoardFilterSelected0 = getFromArray(activityIsBoardFilterSelectedGet, 0);
                        // read activity.isBoardFilterSelected.get()[2]
                        activityIsBoardFilterSelected2 = getFromArray(activityIsBoardFilterSelectedGet, 2);
                    }


                    // read android.databinding.DynamicUtil.safeUnbox(activity.isBoardFilterSelected.get()[1])
                    androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected1 = android.databinding.DynamicUtil.safeUnbox(activityIsBoardFilterSelected1);
                    // read android.databinding.DynamicUtil.safeUnbox(activity.isBoardFilterSelected.get()[3])
                    androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected3 = android.databinding.DynamicUtil.safeUnbox(activityIsBoardFilterSelected3);
                    // read android.databinding.DynamicUtil.safeUnbox(activity.isBoardFilterSelected.get()[0])
                    androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected0 = android.databinding.DynamicUtil.safeUnbox(activityIsBoardFilterSelected0);
                    // read android.databinding.DynamicUtil.safeUnbox(activity.isBoardFilterSelected.get()[2])
                    androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected2 = android.databinding.DynamicUtil.safeUnbox(activityIsBoardFilterSelected2);
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (activity != null) {
                        // read activity.selectedCarFilter
                        activitySelectedCarFilter = activity.selectedCarFilter;
                    }
                    updateRegistration(2, activitySelectedCarFilter);


                    if (activitySelectedCarFilter != null) {
                        // read activity.selectedCarFilter.get()
                        activitySelectedCarFilterGet = activitySelectedCarFilter.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            this.buttonFilterAll.setOnClickListener(mCallback13);
            this.buttonFilterDiy.setOnClickListener(mCallback16);
            this.buttonFilterFree.setOnClickListener(mCallback14);
            this.buttonFilterSell.setOnClickListener(mCallback15);
            this.mboundView6.setOnClickListener(mCallback17);
            this.mboundView8.setOnClickListener(mCallback19);
            this.textCarbrandHint.setOnClickListener(mCallback18);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.buttonFilterAll.setSelected(androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected0);
            this.buttonFilterDiy.setSelected(androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected3);
            this.buttonFilterFree.setSelected(androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected1);
            this.buttonFilterSell.setSelected(androidDatabindingDynamicUtilSafeUnboxActivityIsBoardFilterSelected2);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView9, activitySelectedCarFilterGet);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.searchText, activitySearchInputTextGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 3: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.onBoardFilterSelected(2);
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.onBoardFilterSelected(3);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.onBoardFilterSelected(0);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {



                    activity.onBoardFilterSelected(1);
                }
                break;
            }
            case 7: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onCarSearchClicked();
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onCarSearchClicked();
                }
                break;
            }
            case 6: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b2_community.BoardFilterActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onCarSearchClicked();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityBoardFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityBoardFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityBoardFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_board_filter, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityBoardFilterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityBoardFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.activity_board_filter, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityBoardFilterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityBoardFilterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_board_filter_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityBoardFilterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity.searchInputText
        flag 1 (0x2L): activity.isBoardFilterSelected
        flag 2 (0x3L): activity.selectedCarFilter
        flag 3 (0x4L): activity
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}