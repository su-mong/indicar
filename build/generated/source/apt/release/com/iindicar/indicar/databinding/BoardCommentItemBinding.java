package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardCommentItemBinding extends android.databinding.ViewDataBinding  {

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
    public final android.widget.ImageView imageUserProfile;
    @NonNull
    private final android.support.v7.widget.CardView mboundView0;
    @NonNull
    public final android.widget.TextView textContent;
    @NonNull
    public final android.widget.TextView textDate;
    @NonNull
    public final android.widget.TextView textUserName;
    // variables
    @Nullable
    private com.iindicar.indicar.data.vo.BoardCommentVO mComment;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardCommentItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.imageUserProfile = (android.widget.ImageView) bindings[1];
        this.imageUserProfile.setTag(null);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textContent = (android.widget.TextView) bindings[4];
        this.textContent.setTag(null);
        this.textDate = (android.widget.TextView) bindings[3];
        this.textDate.setTag(null);
        this.textUserName = (android.widget.TextView) bindings[2];
        this.textUserName.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
        if (BR.comment == variableId) {
            setComment((com.iindicar.indicar.data.vo.BoardCommentVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setComment(@Nullable com.iindicar.indicar.data.vo.BoardCommentVO Comment) {
        updateRegistration(0, Comment);
        this.mComment = Comment;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.comment);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.BoardCommentVO getComment() {
        return mComment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeComment((com.iindicar.indicar.data.vo.BoardCommentVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeComment(com.iindicar.indicar.data.vo.BoardCommentVO Comment, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.userProfileUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.userName) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.firstTime) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.content) {
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
        java.lang.String commentContent = null;
        java.lang.String commentFirstTime = null;
        java.lang.String commentUserProfileUrl = null;
        com.iindicar.indicar.data.vo.BoardCommentVO comment = mComment;
        java.lang.String commentUserName = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (comment != null) {
                        // read comment.content
                        commentContent = comment.getContent();
                    }
            }
            if ((dirtyFlags & 0x29L) != 0) {

                    if (comment != null) {
                        // read comment.firstTime
                        commentFirstTime = comment.getFirstTime();
                    }
            }
            if ((dirtyFlags & 0x23L) != 0) {

                    if (comment != null) {
                        // read comment.userProfileUrl
                        commentUserProfileUrl = comment.getUserProfileUrl();
                    }
            }
            if ((dirtyFlags & 0x25L) != 0) {

                    if (comment != null) {
                        // read comment.userName
                        commentUserName = comment.getUserName();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x23L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadCircleImage(this.imageUserProfile, commentUserProfileUrl, (android.graphics.drawable.Drawable)null);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textContent, commentContent);
        }
        if ((dirtyFlags & 0x29L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.DateBinding.convertDateToDisplayText(this.textDate, commentFirstTime);
        }
        if ((dirtyFlags & 0x25L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textUserName, commentUserName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardCommentItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardCommentItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardCommentItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_comment_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardCommentItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardCommentItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_comment_item, null, false), bindingComponent);
    }
    @NonNull
    public static BoardCommentItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardCommentItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_comment_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardCommentItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): comment
        flag 1 (0x2L): comment.userProfileUrl
        flag 2 (0x3L): comment.userName
        flag 3 (0x4L): comment.firstTime
        flag 4 (0x5L): comment.content
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}