package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardListFragmentBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnRefreshListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.scrollview, 5);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonBoardWrite;
    @NonNull
    public final android.widget.ImageButton buttonFastUp;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerViewBoardContainer;
    @NonNull
    public final android.support.v4.widget.SwipeRefreshLayout refreshLayout;
    @NonNull
    public final android.support.v4.widget.NestedScrollView scrollview;
    // variables
    @Nullable
    private com.iindicar.indicar.b2_community.boardList.BoardListFragment mFragment;
    @Nullable
    private com.iindicar.indicar.b2_community.boardList.BoardListViewModel mViewModel;
    @Nullable
    private final android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardListFragmentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 4);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.buttonBoardWrite = (android.widget.ImageButton) bindings[4];
        this.buttonBoardWrite.setTag(null);
        this.buttonFastUp = (android.widget.ImageButton) bindings[3];
        this.buttonFastUp.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerViewBoardContainer = (android.support.v7.widget.RecyclerView) bindings[2];
        this.recyclerViewBoardContainer.setTag(null);
        this.refreshLayout = (android.support.v4.widget.SwipeRefreshLayout) bindings[1];
        this.refreshLayout.setTag(null);
        this.scrollview = (android.support.v4.widget.NestedScrollView) bindings[5];
        setRootTag(root);
        // listeners
        mCallback1 = new android.databinding.generated.callback.OnRefreshListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
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
        if (BR.fragment == variableId) {
            setFragment((com.iindicar.indicar.b2_community.boardList.BoardListFragment) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.iindicar.indicar.b2_community.boardList.BoardListViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.iindicar.indicar.b2_community.boardList.BoardListFragment Fragment) {
        this.mFragment = Fragment;
    }
    @Nullable
    public com.iindicar.indicar.b2_community.boardList.BoardListFragment getFragment() {
        return mFragment;
    }
    public void setViewModel(@Nullable com.iindicar.indicar.b2_community.boardList.BoardListViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b2_community.boardList.BoardListViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelIsPageUpScrolling((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeViewModelIsScrolling((android.databinding.ObservableBoolean) object, fieldId);
            case 2 :
                return onChangeViewModelIsDataLoading((android.databinding.ObservableBoolean) object, fieldId);
            case 3 :
                return onChangeViewModelIsVerticalScrolling((android.databinding.ObservableBoolean) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelIsPageUpScrolling(android.databinding.ObservableBoolean ViewModelIsPageUpScrolling, int fieldId) {
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
    private boolean onChangeViewModelIsDataLoading(android.databinding.ObservableBoolean ViewModelIsDataLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsVerticalScrolling(android.databinding.ObservableBoolean ViewModelIsVerticalScrolling, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        android.databinding.ObservableBoolean viewModelIsPageUpScrolling = null;
        boolean viewModelIsScrollingGet = false;
        android.databinding.ObservableBoolean viewModelIsScrolling = null;
        boolean viewModelIsPageUpScrollingGet = false;
        int viewModelIsScrollingBooleanTrueViewModelIsVerticalScrollingViewGONEViewVISIBLE = 0;
        boolean viewModelIsDataLoading = false;
        boolean viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalse = false;
        int viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalseViewVISIBLEViewGONE = 0;
        boolean viewModelIsScrollingBooleanTrueViewModelIsVerticalScrolling = false;
        android.databinding.ObservableBoolean ViewModelIsDataLoading1 = null;
        boolean viewModelIsDataLoadingGet = false;
        android.databinding.ObservableBoolean viewModelIsVerticalScrolling = null;
        boolean viewModelIsVerticalScrollingGet = false;
        com.iindicar.indicar.b2_community.boardList.BoardListViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x6fL) != 0) {


            if ((dirtyFlags & 0x65L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isPageUpScrolling
                        viewModelIsPageUpScrolling = viewModel.isPageUpScrolling;
                    }
                    updateRegistration(0, viewModelIsPageUpScrolling);


                    if (viewModelIsPageUpScrolling != null) {
                        // read viewModel.isPageUpScrolling.get()
                        viewModelIsPageUpScrollingGet = viewModelIsPageUpScrolling.get();
                    }
                if((dirtyFlags & 0x65L) != 0) {
                    if(viewModelIsPageUpScrollingGet) {
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x200L;
                    }
                }
            }
            if ((dirtyFlags & 0x6aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isScrolling
                        viewModelIsScrolling = viewModel.isScrolling;
                    }
                    updateRegistration(1, viewModelIsScrolling);


                    if (viewModelIsScrolling != null) {
                        // read viewModel.isScrolling.get()
                        viewModelIsScrollingGet = viewModelIsScrolling.get();
                    }
                if((dirtyFlags & 0x6aL) != 0) {
                    if(viewModelIsScrollingGet) {
                            dirtyFlags |= 0x4000L;
                    }
                    else {
                            dirtyFlags |= 0x2000L;
                    }
                }
            }
            if ((dirtyFlags & 0x64L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isDataLoading
                        ViewModelIsDataLoading1 = viewModel.isDataLoading;
                    }
                    updateRegistration(2, ViewModelIsDataLoading1);


                    if (ViewModelIsDataLoading1 != null) {
                        // read viewModel.isDataLoading.get()
                        viewModelIsDataLoadingGet = ViewModelIsDataLoading1.get();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x400L) != 0) {

                if (viewModel != null) {
                    // read viewModel.isDataLoading
                    ViewModelIsDataLoading1 = viewModel.isDataLoading;
                }
                updateRegistration(2, ViewModelIsDataLoading1);


                if (ViewModelIsDataLoading1 != null) {
                    // read viewModel.isDataLoading.get()
                    viewModelIsDataLoadingGet = ViewModelIsDataLoading1.get();
                }


                // read !viewModel.isDataLoading.get()
                viewModelIsDataLoading = !viewModelIsDataLoadingGet;
        }
        if ((dirtyFlags & 0x2000L) != 0) {

                if (viewModel != null) {
                    // read viewModel.isVerticalScrolling
                    viewModelIsVerticalScrolling = viewModel.isVerticalScrolling;
                }
                updateRegistration(3, viewModelIsVerticalScrolling);


                if (viewModelIsVerticalScrolling != null) {
                    // read viewModel.isVerticalScrolling.get()
                    viewModelIsVerticalScrollingGet = viewModelIsVerticalScrolling.get();
                }
        }

        if ((dirtyFlags & 0x65L) != 0) {

                // read viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false
                viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalse = ((viewModelIsPageUpScrollingGet) ? (viewModelIsDataLoading) : (false));
            if((dirtyFlags & 0x65L) != 0) {
                if(viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalse) {
                        dirtyFlags |= 0x1000L;
                }
                else {
                        dirtyFlags |= 0x800L;
                }
            }


                // read viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false ? View.VISIBLE : View.GONE
                viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalseViewVISIBLEViewGONE = ((viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalse) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        if ((dirtyFlags & 0x6aL) != 0) {

                // read viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get()
                viewModelIsScrollingBooleanTrueViewModelIsVerticalScrolling = ((viewModelIsScrollingGet) ? (true) : (viewModelIsVerticalScrollingGet));
            if((dirtyFlags & 0x6aL) != 0) {
                if(viewModelIsScrollingBooleanTrueViewModelIsVerticalScrolling) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }


                // read viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get() ? View.GONE : View.VISIBLE
                viewModelIsScrollingBooleanTrueViewModelIsVerticalScrollingViewGONEViewVISIBLE = ((viewModelIsScrollingBooleanTrueViewModelIsVerticalScrolling) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x6aL) != 0) {
            // api target 1

            this.buttonBoardWrite.setVisibility(viewModelIsScrollingBooleanTrueViewModelIsVerticalScrollingViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 0x65L) != 0) {
            // api target 1

            this.buttonFastUp.setVisibility(viewModelIsPageUpScrollingViewModelIsDataLoadingBooleanFalseViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x60L) != 0) {
            // api target 1

            com.iindicar.indicar.b2_community.boardList.BoardListBinding.setLayoutManager(this.recyclerViewBoardContainer, viewModel);
        }
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            this.refreshLayout.setOnRefreshListener(mCallback1);
        }
        if ((dirtyFlags & 0x64L) != 0) {
            // api target 1

            this.refreshLayout.setRefreshing(viewModelIsDataLoadingGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnRefresh(int sourceId ) {
        // localize variables for thread safety
        // viewModel
        com.iindicar.indicar.b2_community.boardList.BoardListViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {



            viewModel.onRefresh(recyclerViewBoardContainer);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardListFragmentBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_list_fragment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_list_fragment, null, false), bindingComponent);
    }
    @NonNull
    public static BoardListFragmentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardListFragmentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_list_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardListFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel.isPageUpScrolling
        flag 1 (0x2L): viewModel.isScrolling
        flag 2 (0x3L): viewModel.isDataLoading
        flag 3 (0x4L): viewModel.isVerticalScrolling
        flag 4 (0x5L): fragment
        flag 5 (0x6L): viewModel
        flag 6 (0x7L): null
        flag 7 (0x8L): viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get() ? View.GONE : View.VISIBLE
        flag 8 (0x9L): viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get() ? View.GONE : View.VISIBLE
        flag 9 (0xaL): viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false
        flag 10 (0xbL): viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false
        flag 11 (0xcL): viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false ? View.VISIBLE : View.GONE
        flag 12 (0xdL): viewModel.isPageUpScrolling.get() ? !viewModel.isDataLoading.get() : false ? View.VISIBLE : View.GONE
        flag 13 (0xeL): viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get()
        flag 14 (0xfL): viewModel.isScrolling.get() ? true : viewModel.isVerticalScrolling.get()
    flag mapping end*/
    //end
}