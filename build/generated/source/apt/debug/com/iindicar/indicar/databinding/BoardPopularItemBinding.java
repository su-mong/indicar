package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardPopularItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.container, 10);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout container;
    @NonNull
    public final android.widget.ImageView imageMain;
    @NonNull
    private final android.support.v7.widget.CardView mboundView0;
    @NonNull
    private final android.widget.ImageButton mboundView2;
    @NonNull
    private final android.widget.ImageView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView8;
    @NonNull
    public final android.widget.TextView textBoardContent;
    @NonNull
    public final android.widget.TextView textBoardType;
    @NonNull
    public final android.widget.TextView textComment;
    @NonNull
    public final android.widget.TextView textLike;
    @NonNull
    public final android.widget.TextView textUserName;
    // variables
    @Nullable
    private com.iindicar.indicar.data.vo.BoardDetailVO mItem;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardPopularItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.container = (android.widget.LinearLayout) bindings[10];
        this.imageMain = (android.widget.ImageView) bindings[1];
        this.imageMain.setTag(null);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageButton) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView4 = (android.widget.ImageView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.textBoardContent = (android.widget.TextView) bindings[9];
        this.textBoardContent.setTag(null);
        this.textBoardType = (android.widget.TextView) bindings[7];
        this.textBoardType.setTag(null);
        this.textComment = (android.widget.TextView) bindings[6];
        this.textComment.setTag(null);
        this.textLike = (android.widget.TextView) bindings[5];
        this.textLike.setTag(null);
        this.textUserName = (android.widget.TextView) bindings[3];
        this.textUserName.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
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
            setItem((com.iindicar.indicar.data.vo.BoardDetailVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.data.vo.BoardDetailVO Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.BoardDetailVO getItem() {
        return mItem;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.data.vo.BoardDetailVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.iindicar.indicar.data.vo.BoardDetailVO Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.mainImageUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.userProfileUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.userName) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.boardType) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.likeCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.commentCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        else if (fieldId == BR.firstDate) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.boardContent) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
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
        com.iindicar.indicar.data.vo.BoardDetailVO item = mItem;
        java.lang.String itemBoardContent = null;
        java.lang.String itemUserProfileUrl = null;
        java.lang.String itemBoardType = null;
        java.lang.String itemLikeCount = null;
        java.lang.String itemUserName = null;
        java.lang.String itemFirstDate = null;
        java.lang.String itemMainImageUrl = null;
        java.lang.String itemCommentCount = null;

        if ((dirtyFlags & 0x3ffL) != 0) {


            if ((dirtyFlags & 0x301L) != 0) {

                    if (item != null) {
                        // read item.boardContent
                        itemBoardContent = item.getBoardContent();
                    }
            }
            if ((dirtyFlags & 0x205L) != 0) {

                    if (item != null) {
                        // read item.userProfileUrl
                        itemUserProfileUrl = item.getUserProfileUrl();
                    }
            }
            if ((dirtyFlags & 0x211L) != 0) {

                    if (item != null) {
                        // read item.boardType
                        itemBoardType = item.getBoardType();
                    }
            }
            if ((dirtyFlags & 0x221L) != 0) {

                    if (item != null) {
                        // read item.likeCount
                        itemLikeCount = item.getLikeCount();
                    }
            }
            if ((dirtyFlags & 0x209L) != 0) {

                    if (item != null) {
                        // read item.userName
                        itemUserName = item.getUserName();
                    }
            }
            if ((dirtyFlags & 0x281L) != 0) {

                    if (item != null) {
                        // read item.firstDate
                        itemFirstDate = item.getFirstDate();
                    }
            }
            if ((dirtyFlags & 0x203L) != 0) {

                    if (item != null) {
                        // read item.mainImageUrl
                        itemMainImageUrl = item.getMainImageUrl();
                    }
            }
            if ((dirtyFlags & 0x241L) != 0) {

                    if (item != null) {
                        // read item.commentCount
                        itemCommentCount = item.getCommentCount();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x203L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.imageMain, itemMainImageUrl);
        }
        if ((dirtyFlags & 0x205L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadCircleImage(this.mboundView2, itemUserProfileUrl, (android.graphics.drawable.Drawable)null);
        }
        if ((dirtyFlags & 0x211L) != 0) {
            // api target 1

            com.iindicar.indicar.b2_community.boardList.BoardListBinding.bindFilterImage(this.mboundView4, itemBoardType);
            com.iindicar.indicar.binding.BoardNameBinding.bindBoardTypeToName(this.textBoardType, itemBoardType);
        }
        if ((dirtyFlags & 0x281L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.DateBinding.convertDateToDisplayText(this.mboundView8, itemFirstDate);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textBoardContent, itemBoardContent);
        }
        if ((dirtyFlags & 0x241L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textComment, itemCommentCount);
        }
        if ((dirtyFlags & 0x221L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textLike, itemLikeCount);
        }
        if ((dirtyFlags & 0x209L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textUserName, itemUserName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardPopularItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardPopularItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardPopularItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_popular_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardPopularItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardPopularItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_popular_item, null, false), bindingComponent);
    }
    @NonNull
    public static BoardPopularItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardPopularItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_popular_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardPopularItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.mainImageUrl
        flag 2 (0x3L): item.userProfileUrl
        flag 3 (0x4L): item.userName
        flag 4 (0x5L): item.boardType
        flag 5 (0x6L): item.likeCount
        flag 6 (0x7L): item.commentCount
        flag 7 (0x8L): item.firstDate
        flag 8 (0x9L): item.boardContent
        flag 9 (0xaL): null
    flag mapping end*/
    //end
}