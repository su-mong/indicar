package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActionBarLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonLeft;
    @NonNull
    public final android.widget.ImageView imageCenter;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    @Nullable
    private com.iindicar.indicar.BaseActivity mActivity;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActionBarLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.buttonLeft = (android.widget.ImageButton) bindings[1];
        this.buttonLeft.setTag(null);
        this.imageCenter = (android.widget.ImageView) bindings[2];
        this.imageCenter.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
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
            setActivity((com.iindicar.indicar.BaseActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.BaseActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.BaseActivity getActivity() {
        return mActivity;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityCenterImageId((android.databinding.ObservableInt) object, fieldId);
            case 1 :
                return onChangeActivityLeftImageId((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityCenterImageId(android.databinding.ObservableInt ActivityCenterImageId, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityLeftImageId(android.databinding.ObservableInt ActivityLeftImageId, int fieldId) {
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
        boolean activityLeftImageIdInt0 = false;
        android.databinding.ObservableInt activityCenterImageId = null;
        com.iindicar.indicar.BaseActivity activity = mActivity;
        int activityLeftImageIdGet = 0;
        int activityLeftImageIdInt0ViewGONEViewVISIBLE = 0;
        android.databinding.ObservableInt activityLeftImageId = null;
        int activityCenterImageIdGet = 0;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (activity != null) {
                        // read activity.centerImageId
                        activityCenterImageId = activity.centerImageId;
                    }
                    updateRegistration(0, activityCenterImageId);


                    if (activityCenterImageId != null) {
                        // read activity.centerImageId.get()
                        activityCenterImageIdGet = activityCenterImageId.get();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (activity != null) {
                        // read activity.leftImageId
                        activityLeftImageId = activity.leftImageId;
                    }
                    updateRegistration(1, activityLeftImageId);


                    if (activityLeftImageId != null) {
                        // read activity.leftImageId.get()
                        activityLeftImageIdGet = activityLeftImageId.get();
                    }


                    // read activity.leftImageId.get() == 0
                    activityLeftImageIdInt0 = (activityLeftImageIdGet) == (0);
                if((dirtyFlags & 0xeL) != 0) {
                    if(activityLeftImageIdInt0) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read activity.leftImageId.get() == 0 ? View.GONE : View.VISIBLE
                    activityLeftImageIdInt0ViewGONEViewVISIBLE = ((activityLeftImageIdInt0) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.buttonLeft.setVisibility(activityLeftImageIdInt0ViewGONEViewVISIBLE);
            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.buttonLeft, activityLeftImageIdGet);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.imageCenter, activityCenterImageIdGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActionBarLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActionBarLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActionBarLayoutBinding>inflate(inflater, com.iindicar.indicar.R.layout.action_bar_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActionBarLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActionBarLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.action_bar_layout, null, false), bindingComponent);
    }
    @NonNull
    public static ActionBarLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActionBarLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/action_bar_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActionBarLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity.centerImageId
        flag 1 (0x2L): activity.leftImageId
        flag 2 (0x3L): activity
        flag 3 (0x4L): null
        flag 4 (0x5L): activity.leftImageId.get() == 0 ? View.GONE : View.VISIBLE
        flag 5 (0x6L): activity.leftImageId.get() == 0 ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}