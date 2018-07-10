package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardWriteItemFragmentBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.page_container, 7);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonAddPage;
    @NonNull
    public final android.widget.ImageButton buttonDeletePage;
    @NonNull
    public final android.widget.Button buttonNextPage;
    @NonNull
    public final android.widget.Button buttonPrevPage;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView pageContainer;
    @NonNull
    public final android.widget.TextView textCurrentPage;
    @NonNull
    public final android.widget.TextView textTotalPage;
    // variables
    @Nullable
    private com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel mViewModel;
    @Nullable
    private final android.view.View.OnClickListener mCallback23;
    @Nullable
    private final android.view.View.OnClickListener mCallback21;
    @Nullable
    private final android.view.View.OnClickListener mCallback20;
    @Nullable
    private final android.view.View.OnClickListener mCallback22;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardWriteItemFragmentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.buttonAddPage = (android.widget.ImageButton) bindings[5];
        this.buttonAddPage.setTag(null);
        this.buttonDeletePage = (android.widget.ImageButton) bindings[6];
        this.buttonDeletePage.setTag(null);
        this.buttonNextPage = (android.widget.Button) bindings[4];
        this.buttonNextPage.setTag(null);
        this.buttonPrevPage = (android.widget.Button) bindings[1];
        this.buttonPrevPage.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pageContainer = (android.support.v7.widget.RecyclerView) bindings[7];
        this.textCurrentPage = (android.widget.TextView) bindings[2];
        this.textCurrentPage.setTag(null);
        this.textTotalPage = (android.widget.TextView) bindings[3];
        this.textTotalPage.setTag(null);
        setRootTag(root);
        // listeners
        mCallback23 = new android.databinding.generated.callback.OnClickListener(this, 4);
        mCallback21 = new android.databinding.generated.callback.OnClickListener(this, 2);
        mCallback20 = new android.databinding.generated.callback.OnClickListener(this, 1);
        mCallback22 = new android.databinding.generated.callback.OnClickListener(this, 3);
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
        if (BR.viewModel == variableId) {
            setViewModel((com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelTotalPage((android.databinding.ObservableInt) object, fieldId);
            case 1 :
                return onChangeViewModelCurrentPage((android.databinding.ObservableInt) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelTotalPage(android.databinding.ObservableInt ViewModelTotalPage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCurrentPage(android.databinding.ObservableInt ViewModelCurrentPage, int fieldId) {
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
        java.lang.String stringValueOfViewModelTotalPage = null;
        java.lang.String stringValueOfViewModelCurrentPageInt1 = null;
        int viewModelCurrentPageInt1 = 0;
        int viewModelTotalPageInt1 = 0;
        boolean viewModelCurrentPageViewModelTotalPageInt1 = false;
        android.databinding.ObservableInt viewModelTotalPage = null;
        android.databinding.ObservableInt viewModelCurrentPage = null;
        int viewModelCurrentPageGet = 0;
        boolean viewModelCurrentPageInt0 = false;
        com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
        int viewModelTotalPageGet = 0;

        if ((dirtyFlags & 0xfL) != 0) {



                if (viewModel != null) {
                    // read viewModel.totalPage
                    viewModelTotalPage = viewModel.totalPage;
                    // read viewModel.currentPage
                    viewModelCurrentPage = viewModel.currentPage;
                }
                updateRegistration(0, viewModelTotalPage);
                updateRegistration(1, viewModelCurrentPage);


                if (viewModelTotalPage != null) {
                    // read viewModel.totalPage.get()
                    viewModelTotalPageGet = viewModelTotalPage.get();
                }
                if (viewModelCurrentPage != null) {
                    // read viewModel.currentPage.get()
                    viewModelCurrentPageGet = viewModelCurrentPage.get();
                }

            if ((dirtyFlags & 0xdL) != 0) {

                    // read String.valueOf(viewModel.totalPage.get())
                    stringValueOfViewModelTotalPage = java.lang.String.valueOf(viewModelTotalPageGet);
            }

                // read (viewModel.totalPage.get()) - (1)
                viewModelTotalPageInt1 = (viewModelTotalPageGet) - (1);


                // read viewModel.currentPage.get() != (viewModel.totalPage.get()) - (1)
                viewModelCurrentPageViewModelTotalPageInt1 = (viewModelCurrentPageGet) != (viewModelTotalPageInt1);
            if ((dirtyFlags & 0xeL) != 0) {

                    // read (viewModel.currentPage.get()) + (1)
                    viewModelCurrentPageInt1 = (viewModelCurrentPageGet) + (1);
                    // read viewModel.currentPage.get() != 0
                    viewModelCurrentPageInt0 = (viewModelCurrentPageGet) != (0);


                    // read String.valueOf((viewModel.currentPage.get()) + (1))
                    stringValueOfViewModelCurrentPageInt1 = java.lang.String.valueOf(viewModelCurrentPageInt1);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.buttonAddPage.setOnClickListener(mCallback22);
            this.buttonDeletePage.setOnClickListener(mCallback23);
            this.buttonNextPage.setOnClickListener(mCallback21);
            this.buttonPrevPage.setOnClickListener(mCallback20);
        }
        if ((dirtyFlags & 0xfL) != 0) {
            // api target 1

            this.buttonNextPage.setEnabled(viewModelCurrentPageViewModelTotalPageInt1);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.buttonPrevPage.setEnabled(viewModelCurrentPageInt0);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.textCurrentPage, stringValueOfViewModelCurrentPageInt1);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textTotalPage, stringValueOfViewModelTotalPage);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 4: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.pageRemoveClick();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.onNextPageButtonClicked();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.onPrevPageButtonClicked();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.pageAddClick();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardWriteItemFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardWriteItemFragmentBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_write_item_fragment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardWriteItemFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_write_item_fragment, null, false), bindingComponent);
    }
    @NonNull
    public static BoardWriteItemFragmentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteItemFragmentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_write_item_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardWriteItemFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel.totalPage
        flag 1 (0x2L): viewModel.currentPage
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}