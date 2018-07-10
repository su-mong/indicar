package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardWriteFilterFragmentBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageviewBWF_title, 4);
        sViewsWithIds.put(R.id.text_typeofvehicle, 5);
        sViewsWithIds.put(R.id.button_search_car, 6);
        sViewsWithIds.put(R.id.text_car_name, 7);
        sViewsWithIds.put(R.id.text_typeofpost, 8);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonDayLife;
    @NonNull
    public final android.widget.ImageButton buttonDiy;
    @NonNull
    public final android.widget.ImageButton buttonMarket;
    @NonNull
    public final android.widget.ImageButton buttonSearchCar;
    @NonNull
    public final android.widget.ImageView imageviewBWFTitle;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView textCarName;
    @NonNull
    public final android.widget.ImageView textTypeofpost;
    @NonNull
    public final android.widget.ImageView textTypeofvehicle;
    // variables
    @Nullable
    private com.iindicar.indicar.Constant mConstant;
    @Nullable
    private com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel mViewModel;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardWriteFilterFragmentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 4);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.buttonDayLife = (android.widget.ImageButton) bindings[1];
        this.buttonDayLife.setTag(null);
        this.buttonDiy = (android.widget.ImageButton) bindings[3];
        this.buttonDiy.setTag(null);
        this.buttonMarket = (android.widget.ImageButton) bindings[2];
        this.buttonMarket.setTag(null);
        this.buttonSearchCar = (android.widget.ImageButton) bindings[6];
        this.imageviewBWFTitle = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textCarName = (android.widget.TextView) bindings[7];
        this.textTypeofpost = (android.widget.ImageView) bindings[8];
        this.textTypeofvehicle = (android.widget.ImageView) bindings[5];
        setRootTag(root);
        // listeners
        mCallback2 = new android.databinding.generated.callback.OnClickListener(this, 1);
        mCallback3 = new android.databinding.generated.callback.OnClickListener(this, 2);
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
        if (BR.constant == variableId) {
            setConstant((com.iindicar.indicar.Constant) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setConstant(@Nullable com.iindicar.indicar.Constant Constant) {
        this.mConstant = Constant;
    }
    @Nullable
    public com.iindicar.indicar.Constant getConstant() {
        return mConstant;
    }
    public void setViewModel(@Nullable com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
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
                return onChangeConstantMARKET((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeConstantDAYLIFE((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelBoardType((android.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeConstantDIY((android.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeConstantMARKET(android.databinding.ObservableField<java.lang.String> ConstantMARKET, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConstantDAYLIFE(android.databinding.ObservableField<java.lang.String> ConstantDAYLIFE, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelBoardType(android.databinding.ObservableField<java.lang.String> ViewModelBoardType, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConstantDIY(android.databinding.ObservableField<java.lang.String> ConstantDIY, int fieldId) {
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
        boolean viewModelBoardTypeConstantDIY = false;
        boolean viewModelBoardTypeConstantDAYLIFE = false;
        java.lang.String viewModelBoardTypeGet = null;
        android.databinding.ObservableField<java.lang.String> viewModelBoardType = null;
        com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x64L) != 0) {



                if (viewModel != null) {
                    // read viewModel.boardType
                    viewModelBoardType = viewModel.boardType;
                }
                updateRegistration(2, viewModelBoardType);


                if (viewModelBoardType != null) {
                    // read viewModel.boardType.get()
                    viewModelBoardTypeGet = viewModelBoardType.get();
                }


                // read viewModel.boardType.get() == Constant.DIY.get()
                viewModelBoardTypeConstantDIY = (viewModelBoardTypeGet) == (com.iindicar.indicar.Constant.DIY.get());
                // read viewModel.boardType.get() == Constant.DAY_LIFE.get()
                viewModelBoardTypeConstantDAYLIFE = (viewModelBoardTypeGet) == (com.iindicar.indicar.Constant.DAY_LIFE.get());
        }
        // batch finished
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            this.buttonDayLife.setOnClickListener(mCallback2);
            this.buttonDayLife.setTag(com.iindicar.indicar.Constant.DAY_LIFE.get());
            this.buttonDiy.setOnClickListener(mCallback3);
            this.buttonDiy.setTag(com.iindicar.indicar.Constant.DIY.get());
            this.buttonMarket.setTag(com.iindicar.indicar.Constant.MARKET.get());
        }
        if ((dirtyFlags & 0x64L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BindAdapter.setSelected(this.buttonDayLife, viewModelBoardTypeConstantDAYLIFE);
            com.iindicar.indicar.binding.BindAdapter.setSelected(this.buttonDiy, viewModelBoardTypeConstantDIY);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.setBoardTypeFiltering(callbackArg_0);
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



                    viewModel.setBoardTypeFiltering(callbackArg_0);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardWriteFilterFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteFilterFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardWriteFilterFragmentBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_write_filter_fragment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardWriteFilterFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteFilterFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_write_filter_fragment, null, false), bindingComponent);
    }
    @NonNull
    public static BoardWriteFilterFragmentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardWriteFilterFragmentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_write_filter_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardWriteFilterFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): Constant.MARKET
        flag 1 (0x2L): Constant.DAY_LIFE
        flag 2 (0x3L): viewModel.boardType
        flag 3 (0x4L): Constant.DIY
        flag 4 (0x5L): constant
        flag 5 (0x6L): viewModel
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}