package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTraceBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.constraint_title, 2);
        sViewsWithIds.put(R.id.btnA_writing2, 3);
        sViewsWithIds.put(R.id.ivA_writingOn, 4);
        sViewsWithIds.put(R.id.btnA_like2, 5);
        sViewsWithIds.put(R.id.ivA_likeOn, 6);
        sViewsWithIds.put(R.id.btnA_comment2, 7);
        sViewsWithIds.put(R.id.ivA_commentOn, 8);
    }
    // views
    @NonNull
    public final android.widget.Button btnAComment2;
    @NonNull
    public final android.widget.Button btnALike2;
    @NonNull
    public final android.widget.Button btnAWriting2;
    @NonNull
    public final android.support.constraint.ConstraintLayout constraintTitle;
    @NonNull
    public final android.widget.ImageView ivACommentOn;
    @NonNull
    public final android.widget.ImageView ivALikeOn;
    @NonNull
    public final android.widget.ImageView ivAWritingOn;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerViewBoardContainer;
    // variables
    @Nullable
    private com.iindicar.indicar.b4_account.TraceActivity mActivity;
    @Nullable
    private com.iindicar.indicar.b4_account.TraceViewModel mViewModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTraceBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.btnAComment2 = (android.widget.Button) bindings[7];
        this.btnALike2 = (android.widget.Button) bindings[5];
        this.btnAWriting2 = (android.widget.Button) bindings[3];
        this.constraintTitle = (android.support.constraint.ConstraintLayout) bindings[2];
        this.ivACommentOn = (android.widget.ImageView) bindings[8];
        this.ivALikeOn = (android.widget.ImageView) bindings[6];
        this.ivAWritingOn = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerViewBoardContainer = (android.support.v7.widget.RecyclerView) bindings[1];
        this.recyclerViewBoardContainer.setTag(null);
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
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.b4_account.TraceActivity) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.iindicar.indicar.b4_account.TraceViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.b4_account.TraceActivity Activity) {
        this.mActivity = Activity;
    }
    @Nullable
    public com.iindicar.indicar.b4_account.TraceActivity getActivity() {
        return mActivity;
    }
    public void setViewModel(@Nullable com.iindicar.indicar.b4_account.TraceViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b4_account.TraceViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        com.iindicar.indicar.b4_account.TraceViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x6L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.iindicar.indicar.b4_account.TraceBinding.setLayoutManager(this.recyclerViewBoardContainer, viewModel);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityTraceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTraceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityTraceBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_trace, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityTraceBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTraceBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.activity_trace, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityTraceBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityTraceBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_trace_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityTraceBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}