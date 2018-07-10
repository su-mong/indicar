package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.positive_button, 4);
        sViewsWithIds.put(R.id.positive_image, 5);
        sViewsWithIds.put(R.id.negative_button, 6);
        sViewsWithIds.put(R.id.negative_image, 7);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    public final android.widget.RelativeLayout negativeButton;
    @NonNull
    public final android.widget.ImageView negativeImage;
    @NonNull
    public final android.widget.RelativeLayout positiveButton;
    @NonNull
    public final android.widget.ImageView positiveImage;
    // variables
    @Nullable
    private com.iindicar.indicar.utils.CustomAlertDialog mDialog;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 3);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.negativeButton = (android.widget.RelativeLayout) bindings[6];
        this.negativeImage = (android.widget.ImageView) bindings[7];
        this.positiveButton = (android.widget.RelativeLayout) bindings[4];
        this.positiveImage = (android.widget.ImageView) bindings[5];
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
        if (BR.dialog == variableId) {
            setDialog((com.iindicar.indicar.utils.CustomAlertDialog) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDialog(@Nullable com.iindicar.indicar.utils.CustomAlertDialog Dialog) {
        this.mDialog = Dialog;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.dialog);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.utils.CustomAlertDialog getDialog() {
        return mDialog;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeDialogTitle((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeDialogImageId((android.databinding.ObservableInt) object, fieldId);
            case 2 :
                return onChangeDialogSubTitle((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeDialogTitle(android.databinding.ObservableField<java.lang.String> DialogTitle, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeDialogImageId(android.databinding.ObservableInt DialogImageId, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeDialogSubTitle(android.databinding.ObservableField<java.lang.String> DialogSubTitle, int fieldId) {
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
        java.lang.String dialogTitleGet = null;
        int dialogImageIdGet = 0;
        android.databinding.ObservableField<java.lang.String> dialogTitle = null;
        com.iindicar.indicar.utils.CustomAlertDialog dialog = mDialog;
        android.databinding.ObservableInt dialogImageId = null;
        java.lang.String dialogSubTitleGet = null;
        android.databinding.ObservableField<java.lang.String> dialogSubTitle = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (dialog != null) {
                        // read dialog.title
                        dialogTitle = dialog.title;
                    }
                    updateRegistration(0, dialogTitle);


                    if (dialogTitle != null) {
                        // read dialog.title.get()
                        dialogTitleGet = dialogTitle.get();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (dialog != null) {
                        // read dialog.imageId
                        dialogImageId = dialog.imageId;
                    }
                    updateRegistration(1, dialogImageId);


                    if (dialogImageId != null) {
                        // read dialog.imageId.get()
                        dialogImageIdGet = dialogImageId.get();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (dialog != null) {
                        // read dialog.subTitle
                        dialogSubTitle = dialog.subTitle;
                    }
                    updateRegistration(2, dialogSubTitle);


                    if (dialogSubTitle != null) {
                        // read dialog.subTitle.get()
                        dialogSubTitleGet = dialogSubTitle.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.mboundView1, dialogImageIdGet);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, dialogTitleGet);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, dialogSubTitleGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static DialogLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogLayoutBinding>inflate(inflater, com.iindicar.indicar.R.layout.dialog_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.dialog_layout, null, false), bindingComponent);
    }
    @NonNull
    public static DialogLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): dialog.title
        flag 1 (0x2L): dialog.imageId
        flag 2 (0x3L): dialog.subTitle
        flag 3 (0x4L): dialog
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}