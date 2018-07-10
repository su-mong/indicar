package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardDetailContentLayoutBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recyclerview_board_container, 3);
        sViewsWithIds.put(R.id.recyclerview_comment_container, 4);
        sViewsWithIds.put(R.id.lin_alert_reply_empty, 5);
        sViewsWithIds.put(R.id.imageviewBD_empty, 6);
        sViewsWithIds.put(R.id.comment_box, 7);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout commentBox;
    @NonNull
    public final android.widget.EditText commentText;
    @NonNull
    public final android.widget.ImageView imageviewBDEmpty;
    @NonNull
    public final android.widget.LinearLayout linAlertReplyEmpty;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.ImageButton mboundView2;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerviewBoardContainer;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerviewCommentContainer;
    // variables
    @Nullable
    private com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel mViewModel;
    @Nullable
    private final android.view.View.OnClickListener mCallback40;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardDetailContentLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.commentBox = (android.widget.LinearLayout) bindings[7];
        this.commentText = (android.widget.EditText) bindings[1];
        this.commentText.setTag(null);
        this.imageviewBDEmpty = (android.widget.ImageView) bindings[6];
        this.linAlertReplyEmpty = (android.widget.LinearLayout) bindings[5];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageButton) bindings[2];
        this.mboundView2.setTag(null);
        this.recyclerviewBoardContainer = (android.support.v7.widget.RecyclerView) bindings[3];
        this.recyclerviewCommentContainer = (android.support.v7.widget.RecyclerView) bindings[4];
        setRootTag(root);
        // listeners
        mCallback40 = new android.databinding.generated.callback.OnClickListener(this, 1);
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
        if (BR.viewModel == variableId) {
            setViewModel((com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelCommentWrite((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelCommentWrite(android.databinding.ObservableField<java.lang.String> ViewModelCommentWrite, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        android.databinding.ObservableField<java.lang.String> viewModelCommentWrite = null;
        java.lang.String viewModelCommentWriteGet = null;
        com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.commentWrite
                    viewModelCommentWrite = viewModel.commentWrite;
                }
                updateRegistration(0, viewModelCommentWrite);


                if (viewModelCommentWrite != null) {
                    // read viewModel.commentWrite.get()
                    viewModelCommentWriteGet = viewModelCommentWrite.get();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.commentText, viewModelCommentWriteGet);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView2.setOnClickListener(mCallback40);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.onCommentSubmitClick();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardDetailContentLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailContentLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardDetailContentLayoutBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_detail_content_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardDetailContentLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailContentLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_detail_content_layout, null, false), bindingComponent);
    }
    @NonNull
    public static BoardDetailContentLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailContentLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_detail_content_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardDetailContentLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel.commentWrite
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}