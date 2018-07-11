package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SplashActivityBinding extends android.databinding.ViewDataBinding  {

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
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.widget.ImageView mboundView2;
    // variables
    @Nullable
    private android.databinding.ObservableInt mBottom;
    @Nullable
    private android.databinding.ObservableInt mCenter;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SplashActivityBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.bottom == variableId) {
            setBottom((android.databinding.ObservableInt) variable);
        }
        else if (BR.center == variableId) {
            setCenter((android.databinding.ObservableInt) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBottom(@Nullable android.databinding.ObservableInt Bottom) {
        updateRegistration(0, Bottom);
        this.mBottom = Bottom;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bottom);
        super.requestRebind();
    }
    @Nullable
    public android.databinding.ObservableInt getBottom() {
        return mBottom;
    }
    public void setCenter(@Nullable android.databinding.ObservableInt Center) {
        updateRegistration(1, Center);
        this.mCenter = Center;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.center);
        super.requestRebind();
    }
    @Nullable
    public android.databinding.ObservableInt getCenter() {
        return mCenter;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeBottom((android.databinding.ObservableInt) object, fieldId);
            case 1 :
                return onChangeCenter((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangeBottom(android.databinding.ObservableInt Bottom, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCenter(android.databinding.ObservableInt Center, int fieldId) {
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
        int centerGet = 0;
        android.databinding.ObservableInt bottom = mBottom;
        int bottomGet = 0;
        android.databinding.ObservableInt center = mCenter;

        if ((dirtyFlags & 0x5L) != 0) {



                if (bottom != null) {
                    // read bottom.get()
                    bottomGet = bottom.get();
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (center != null) {
                    // read center.get()
                    centerGet = center.get();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.mboundView1, centerGet);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.mboundView2, bottomGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static SplashActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SplashActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<SplashActivityBinding>inflate(inflater, com.iindicar.indicar.R.layout.splash_activity, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static SplashActivityBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SplashActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.splash_activity, null, false), bindingComponent);
    }
    @NonNull
    public static SplashActivityBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SplashActivityBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/splash_activity_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new SplashActivityBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): bottom
        flag 1 (0x2L): center
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}