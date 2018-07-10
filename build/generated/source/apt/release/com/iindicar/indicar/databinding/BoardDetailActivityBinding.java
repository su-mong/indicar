package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardDetailActivityBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnTouchListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(3, 
            new String[] {"board_detail_content_layout"},
            new int[] {7},
            new int[] {R.layout.board_detail_content_layout});
        sViewsWithIds = null;
    }
    // views
    @Nullable
    public final com.iindicar.indicar.databinding.BoardDetailContentLayoutBinding boardContent;
    @NonNull
    public final android.widget.ImageButton imagebuttonComment;
    @NonNull
    public final android.widget.ImageButton imagebuttonLike;
    @NonNull
    public final android.widget.LinearLayout linearLayout;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final android.widget.RelativeLayout mboundView2;
    @NonNull
    public final android.support.v4.widget.NestedScrollView scrollViewContainer;
    // variables
    @Nullable
    private com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel mViewModel;
    @Nullable
    private final android.view.View.OnTouchListener mCallback42;
    @Nullable
    private final android.view.View.OnTouchListener mCallback41;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardDetailActivityBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 5);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.boardContent = (com.iindicar.indicar.databinding.BoardDetailContentLayoutBinding) bindings[7];
        setContainedBinding(this.boardContent);
        this.imagebuttonComment = (android.widget.ImageButton) bindings[6];
        this.imagebuttonComment.setTag(null);
        this.imagebuttonLike = (android.widget.ImageButton) bindings[5];
        this.imagebuttonLike.setTag(null);
        this.linearLayout = (android.widget.LinearLayout) bindings[4];
        this.linearLayout.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.RelativeLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.scrollViewContainer = (android.support.v4.widget.NestedScrollView) bindings[3];
        this.scrollViewContainer.setTag(null);
        setRootTag(root);
        // listeners
        mCallback42 = new android.databinding.generated.callback.OnTouchListener(this, 2);
        mCallback41 = new android.databinding.generated.callback.OnTouchListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        boardContent.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (boardContent.hasPendingBindings()) {
            return true;
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
            mDirtyFlags |= 0x20L;
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
                return onChangeViewModelIsKeyboardOpen((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeViewModelIsScrolling((android.databinding.ObservableBoolean) object, fieldId);
            case 2 :
                return onChangeBoardContent((com.iindicar.indicar.databinding.BoardDetailContentLayoutBinding) object, fieldId);
            case 3 :
                return onChangeViewModelIsBoardDataLoading((android.databinding.ObservableBoolean) object, fieldId);
            case 4 :
                return onChangeViewModelIsLikeBoard((android.databinding.ObservableBoolean) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelIsKeyboardOpen(android.databinding.ObservableBoolean ViewModelIsKeyboardOpen, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsScrolling(android.databinding.ObservableBoolean ViewModelIsScrolling, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeBoardContent(com.iindicar.indicar.databinding.BoardDetailContentLayoutBinding BoardContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsBoardDataLoading(android.databinding.ObservableBoolean ViewModelIsBoardDataLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsLikeBoard(android.databinding.ObservableBoolean ViewModelIsLikeBoard, int fieldId) {
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
        boolean viewModelIsScrollingGet = false;
        boolean viewModelIsKeyboardOpen = false;
        boolean viewModelIsScrolling = false;
        android.databinding.ObservableBoolean ViewModelIsKeyboardOpen1 = null;
        android.databinding.ObservableBoolean ViewModelIsScrolling1 = null;
        boolean viewModelIsScrollingViewModelIsKeyboardOpenBooleanFalse = false;
        boolean viewModelIsBoardDataLoading = false;
        android.databinding.ObservableBoolean ViewModelIsBoardDataLoading1 = null;
        android.databinding.ObservableBoolean viewModelIsLikeBoard = null;
        boolean viewModelIsKeyboardOpenGet = false;
        boolean viewModelIsLikeBoardGet = false;
        boolean viewModelIsBoardDataLoadingGet = false;
        com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7bL) != 0) {


            if ((dirtyFlags & 0x63L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isScrolling
                        ViewModelIsScrolling1 = viewModel.isScrolling;
                    }
                    updateRegistration(1, ViewModelIsScrolling1);


                    if (ViewModelIsScrolling1 != null) {
                        // read viewModel.isScrolling.get()
                        viewModelIsScrollingGet = ViewModelIsScrolling1.get();
                    }


                    // read !viewModel.isScrolling.get()
                    viewModelIsScrolling = !viewModelIsScrollingGet;
                if((dirtyFlags & 0x63L) != 0) {
                    if(viewModelIsScrolling) {
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                    }
                }
            }
            if ((dirtyFlags & 0x68L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isBoardDataLoading
                        ViewModelIsBoardDataLoading1 = viewModel.isBoardDataLoading;
                    }
                    updateRegistration(3, ViewModelIsBoardDataLoading1);


                    if (ViewModelIsBoardDataLoading1 != null) {
                        // read viewModel.isBoardDataLoading.get()
                        viewModelIsBoardDataLoadingGet = ViewModelIsBoardDataLoading1.get();
                    }


                    // read !viewModel.isBoardDataLoading.get()
                    viewModelIsBoardDataLoading = !viewModelIsBoardDataLoadingGet;
            }
            if ((dirtyFlags & 0x70L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isLikeBoard
                        viewModelIsLikeBoard = viewModel.isLikeBoard;
                    }
                    updateRegistration(4, viewModelIsLikeBoard);


                    if (viewModelIsLikeBoard != null) {
                        // read viewModel.isLikeBoard.get()
                        viewModelIsLikeBoardGet = viewModelIsLikeBoard.get();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x100L) != 0) {

                if (viewModel != null) {
                    // read viewModel.isKeyboardOpen
                    ViewModelIsKeyboardOpen1 = viewModel.isKeyboardOpen;
                }
                updateRegistration(0, ViewModelIsKeyboardOpen1);


                if (ViewModelIsKeyboardOpen1 != null) {
                    // read viewModel.isKeyboardOpen.get()
                    viewModelIsKeyboardOpenGet = ViewModelIsKeyboardOpen1.get();
                }


                // read !viewModel.isKeyboardOpen.get()
                viewModelIsKeyboardOpen = !viewModelIsKeyboardOpenGet;
        }

        if ((dirtyFlags & 0x63L) != 0) {

                // read !viewModel.isScrolling.get() ? !viewModel.isKeyboardOpen.get() : false
                viewModelIsScrollingViewModelIsKeyboardOpenBooleanFalse = ((viewModelIsScrolling) ? (viewModelIsKeyboardOpen) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x60L) != 0) {
            // api target 1

            this.boardContent.setViewModel(viewModel);
        }
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            this.imagebuttonComment.setOnTouchListener(mCallback42);
            this.imagebuttonLike.setOnTouchListener(mCallback41);
        }
        if ((dirtyFlags & 0x70L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BindAdapter.setSelected(this.imagebuttonLike, viewModelIsLikeBoardGet);
        }
        if ((dirtyFlags & 0x63L) != 0) {
            // api target 1

            this.linearLayout.setVisibility(com.iindicar.indicar.binding.BindConversion.convertBooleanToVisibility(viewModelIsScrollingViewModelIsKeyboardOpenBooleanFalse));
        }
        if ((dirtyFlags & 0x68L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(com.iindicar.indicar.binding.BindConversion.convertBooleanToVisibility(viewModelIsBoardDataLoadingGet));
            this.mboundView2.setVisibility(com.iindicar.indicar.binding.BindConversion.convertBooleanToVisibility(viewModelIsBoardDataLoading));
        }
        executeBindingsOn(boardContent);
    }
    // Listener Stub Implementations
    // callback impls
    public final boolean _internalCallbackOnTouch(int sourceId , android.view.View callbackArg_0, android.view.MotionEvent callbackArg_1) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // viewModel.onCommentButtonClick(v, event)
                boolean viewModelOnCommentButtonClickCallbackArg0CallbackArg1 = false;
                // viewModel
                com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {




                    viewModelOnCommentButtonClickCallbackArg0CallbackArg1 = viewModel.onCommentButtonClick(callbackArg_0, callbackArg_1);
                }
                return viewModelOnCommentButtonClickCallbackArg0CallbackArg1;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardDetail.BoardDetailViewModel viewModel = mViewModel;
                // viewModel.onLikeButtonClick(v, event)
                boolean viewModelOnLikeButtonClickCallbackArg0CallbackArg1 = false;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {




                    viewModelOnLikeButtonClickCallbackArg0CallbackArg1 = viewModel.onLikeButtonClick(callbackArg_0, callbackArg_1);
                }
                return viewModelOnLikeButtonClickCallbackArg0CallbackArg1;
            }
            default: {
                return false;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardDetailActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardDetailActivityBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_detail_activity, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardDetailActivityBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailActivityBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_detail_activity, null, false), bindingComponent);
    }
    @NonNull
    public static BoardDetailActivityBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailActivityBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_detail_activity_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardDetailActivityBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel.isKeyboardOpen
        flag 1 (0x2L): viewModel.isScrolling
        flag 2 (0x3L): boardContent
        flag 3 (0x4L): viewModel.isBoardDataLoading
        flag 4 (0x5L): viewModel.isLikeBoard
        flag 5 (0x6L): viewModel
        flag 6 (0x7L): null
        flag 7 (0x8L): !viewModel.isScrolling.get() ? !viewModel.isKeyboardOpen.get() : false
        flag 8 (0x9L): !viewModel.isScrolling.get() ? !viewModel.isKeyboardOpen.get() : false
    flag mapping end*/
    //end
}