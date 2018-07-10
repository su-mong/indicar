package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardWriteItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.button_from_album, 4);
        sViewsWithIds.put(R.id.button_from_camera, 5);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonFromAlbum;
    @NonNull
    public final android.widget.ImageButton buttonFromCamera;
    @NonNull
    public final android.widget.ImageView imageWrite;
    @NonNull
    public final android.widget.LinearLayout llWriteBtnContainer;
    @NonNull
    private final android.support.v7.widget.CardView mboundView0;
    @NonNull
    public final android.widget.EditText textWrite;
    // variables
    @Nullable
    private com.iindicar.indicar.data.vo.WriteFileVO mItem;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardWriteItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.buttonFromAlbum = (android.widget.ImageButton) bindings[4];
        this.buttonFromCamera = (android.widget.ImageButton) bindings[5];
        this.imageWrite = (android.widget.ImageView) bindings[2];
        this.imageWrite.setTag(null);
        this.llWriteBtnContainer = (android.widget.LinearLayout) bindings[1];
        this.llWriteBtnContainer.setTag(null);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textWrite = (android.widget.EditText) bindings[3];
        this.textWrite.setTag(null);
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
        if (BR.item == variableId) {
            setItem((com.iindicar.indicar.data.vo.WriteFileVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.data.vo.WriteFileVO Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.WriteFileVO getItem() {
        return mItem;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.data.vo.WriteFileVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.iindicar.indicar.data.vo.WriteFileVO Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.imageUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.writeText) {
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
        android.net.Uri itemImageUrl = null;
        com.iindicar.indicar.data.vo.WriteFileVO item = mItem;
        java.lang.String itemWriteText = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xbL) != 0) {

                    if (item != null) {
                        // read item.imageUrl
                        itemImageUrl = item.getImageUrl();
                    }
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (item != null) {
                        // read item.writeText
                        itemWriteText = item.getWriteText();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.imageWrite, itemImageUrl);
        }
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            com.iindicar.indicar.b2_community.boardWrite.BoardWriteBinding.setVisibility(this.imageWrite, item);
            com.iindicar.indicar.b2_community.boardWrite.BoardWriteBinding.setVisibility(this.llWriteBtnContainer, item);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textWrite, itemWriteText);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardWriteItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardWriteItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_write_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardWriteItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_write_item, null, false), bindingComponent);
    }
    @NonNull
    public static BoardWriteItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_write_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardWriteItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.imageUrl
        flag 2 (0x3L): item.writeText
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}