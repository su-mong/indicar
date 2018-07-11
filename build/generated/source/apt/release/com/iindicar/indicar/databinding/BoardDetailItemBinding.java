package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardDetailItemBinding extends android.databinding.ViewDataBinding  {

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
    public final android.widget.ImageView imageviewPhoto;
    @NonNull
    private final android.support.v7.widget.CardView mboundView0;
    @NonNull
    public final android.widget.TextView textviewContent;
    // variables
    @Nullable
    private com.iindicar.indicar.data.vo.BoardFileVO mBoardItem;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardDetailItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.imageviewPhoto = (android.widget.ImageView) bindings[1];
        this.imageviewPhoto.setTag(null);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textviewContent = (android.widget.TextView) bindings[2];
        this.textviewContent.setTag(null);
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
        if (BR.boardItem == variableId) {
            setBoardItem((com.iindicar.indicar.data.vo.BoardFileVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBoardItem(@Nullable com.iindicar.indicar.data.vo.BoardFileVO BoardItem) {
        updateRegistration(0, BoardItem);
        this.mBoardItem = BoardItem;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.boardItem);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.BoardFileVO getBoardItem() {
        return mBoardItem;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeBoardItem((com.iindicar.indicar.data.vo.BoardFileVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeBoardItem(com.iindicar.indicar.data.vo.BoardFileVO BoardItem, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.fileUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.fileContent) {
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
        java.lang.String boardItemFileUrl = null;
        com.iindicar.indicar.data.vo.BoardFileVO boardItem = mBoardItem;
        java.lang.String boardItemFileContent = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xbL) != 0) {

                    if (boardItem != null) {
                        // read boardItem.fileUrl
                        boardItemFileUrl = boardItem.getFileUrl();
                    }
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (boardItem != null) {
                        // read boardItem.fileContent
                        boardItemFileContent = boardItem.getFileContent();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.imageviewPhoto, boardItemFileUrl);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textviewContent, boardItemFileContent);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardDetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardDetailItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_detail_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardDetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_detail_item, null, false), bindingComponent);
    }
    @NonNull
    public static BoardDetailItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_detail_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardDetailItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): boardItem
        flag 1 (0x2L): boardItem.fileUrl
        flag 2 (0x3L): boardItem.fileContent
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}