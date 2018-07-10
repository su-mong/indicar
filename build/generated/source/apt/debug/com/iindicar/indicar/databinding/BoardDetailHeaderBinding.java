package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BoardDetailHeaderBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.button_menu, 9);
        sViewsWithIds.put(R.id.tvBDH_like, 10);
        sViewsWithIds.put(R.id.tvBDH_unit1, 11);
        sViewsWithIds.put(R.id.tvBDH_comment, 12);
        sViewsWithIds.put(R.id.tvBDH_unit2, 13);
    }
    // views
    @NonNull
    public final android.widget.ImageButton buttonMenu;
    @NonNull
    public final android.widget.ImageButton imagebuttonUserProfile;
    @NonNull
    public final android.widget.ImageView imageviewMain;
    @NonNull
    public final android.widget.TextView lastDate;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView textBoardType;
    @NonNull
    public final android.widget.TextView textviewCommentCount;
    @NonNull
    public final android.widget.TextView textviewLikeCount;
    @NonNull
    public final android.widget.TextView textviewMain;
    @NonNull
    public final android.widget.TextView textviewUserName;
    @NonNull
    public final android.widget.TextView tvBDHComment;
    @NonNull
    public final android.widget.TextView tvBDHLike;
    @NonNull
    public final android.widget.TextView tvBDHUnit1;
    @NonNull
    public final android.widget.TextView tvBDHUnit2;
    // variables
    @Nullable
    private com.iindicar.indicar.data.vo.BoardFileVO mItem;
    @Nullable
    private com.iindicar.indicar.data.vo.BoardDetailVO mBoard;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoardDetailHeaderBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.buttonMenu = (android.widget.ImageButton) bindings[9];
        this.imagebuttonUserProfile = (android.widget.ImageButton) bindings[2];
        this.imagebuttonUserProfile.setTag(null);
        this.imageviewMain = (android.widget.ImageView) bindings[1];
        this.imageviewMain.setTag(null);
        this.lastDate = (android.widget.TextView) bindings[7];
        this.lastDate.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textBoardType = (android.widget.TextView) bindings[4];
        this.textBoardType.setTag(null);
        this.textviewCommentCount = (android.widget.TextView) bindings[6];
        this.textviewCommentCount.setTag(null);
        this.textviewLikeCount = (android.widget.TextView) bindings[5];
        this.textviewLikeCount.setTag(null);
        this.textviewMain = (android.widget.TextView) bindings[8];
        this.textviewMain.setTag(null);
        this.textviewUserName = (android.widget.TextView) bindings[3];
        this.textviewUserName.setTag(null);
        this.tvBDHComment = (android.widget.TextView) bindings[12];
        this.tvBDHLike = (android.widget.TextView) bindings[10];
        this.tvBDHUnit1 = (android.widget.TextView) bindings[11];
        this.tvBDHUnit2 = (android.widget.TextView) bindings[13];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x400L;
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
            setItem((com.iindicar.indicar.data.vo.BoardFileVO) variable);
        }
        else if (BR.board == variableId) {
            setBoard((com.iindicar.indicar.data.vo.BoardDetailVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.iindicar.indicar.data.vo.BoardFileVO Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.BoardFileVO getItem() {
        return mItem;
    }
    public void setBoard(@Nullable com.iindicar.indicar.data.vo.BoardDetailVO Board) {
        updateRegistration(1, Board);
        this.mBoard = Board;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.board);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.BoardDetailVO getBoard() {
        return mBoard;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.iindicar.indicar.data.vo.BoardFileVO) object, fieldId);
            case 1 :
                return onChangeBoard((com.iindicar.indicar.data.vo.BoardDetailVO) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.iindicar.indicar.data.vo.BoardFileVO Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.fileUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.fileContent) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeBoard(com.iindicar.indicar.data.vo.BoardDetailVO Board, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.userProfileUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.userName) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.boardType) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        else if (fieldId == BR.likeCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.commentCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        else if (fieldId == BR.firstDate) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
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
        java.lang.String boardBoardType = null;
        com.iindicar.indicar.data.vo.BoardFileVO item = mItem;
        java.lang.String boardCommentCount = null;
        java.lang.String boardUserName = null;
        com.iindicar.indicar.data.vo.BoardDetailVO board = mBoard;
        java.lang.String boardUserProfileUrl = null;
        java.lang.String itemFileContent = null;
        java.lang.String boardLikeCount = null;
        java.lang.String itemFileUrl = null;
        java.lang.String boardFirstDate = null;

        if ((dirtyFlags & 0x40dL) != 0) {


            if ((dirtyFlags & 0x409L) != 0) {

                    if (item != null) {
                        // read item.fileContent
                        itemFileContent = item.getFileContent();
                    }
            }
            if ((dirtyFlags & 0x405L) != 0) {

                    if (item != null) {
                        // read item.fileUrl
                        itemFileUrl = item.getFileUrl();
                    }
            }
        }
        if ((dirtyFlags & 0x7f2L) != 0) {


            if ((dirtyFlags & 0x442L) != 0) {

                    if (board != null) {
                        // read board.boardType
                        boardBoardType = board.getBoardType();
                    }
            }
            if ((dirtyFlags & 0x502L) != 0) {

                    if (board != null) {
                        // read board.commentCount
                        boardCommentCount = board.getCommentCount();
                    }
            }
            if ((dirtyFlags & 0x422L) != 0) {

                    if (board != null) {
                        // read board.userName
                        boardUserName = board.getUserName();
                    }
            }
            if ((dirtyFlags & 0x412L) != 0) {

                    if (board != null) {
                        // read board.userProfileUrl
                        boardUserProfileUrl = board.getUserProfileUrl();
                    }
            }
            if ((dirtyFlags & 0x482L) != 0) {

                    if (board != null) {
                        // read board.likeCount
                        boardLikeCount = board.getLikeCount();
                    }
            }
            if ((dirtyFlags & 0x602L) != 0) {

                    if (board != null) {
                        // read board.firstDate
                        boardFirstDate = board.getFirstDate();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x412L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadCircleImage(this.imagebuttonUserProfile, boardUserProfileUrl, (android.graphics.drawable.Drawable)null);
        }
        if ((dirtyFlags & 0x405L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadImage(this.imageviewMain, itemFileUrl);
        }
        if ((dirtyFlags & 0x602L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.DateBinding.convertDateToDisplayText(this.lastDate, boardFirstDate);
        }
        if ((dirtyFlags & 0x442L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BoardNameBinding.bindBoardTypeToName(this.textBoardType, boardBoardType);
        }
        if ((dirtyFlags & 0x502L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textviewCommentCount, boardCommentCount);
        }
        if ((dirtyFlags & 0x482L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textviewLikeCount, boardLikeCount);
        }
        if ((dirtyFlags & 0x409L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textviewMain, itemFileContent);
        }
        if ((dirtyFlags & 0x422L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textviewUserName, boardUserName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoardDetailHeaderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailHeaderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoardDetailHeaderBinding>inflate(inflater, com.iindicar.indicar.R.layout.board_detail_header, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoardDetailHeaderBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailHeaderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.board_detail_header, null, false), bindingComponent);
    }
    @NonNull
    public static BoardDetailHeaderBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoardDetailHeaderBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/board_detail_header_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoardDetailHeaderBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): board
        flag 2 (0x3L): item.fileUrl
        flag 3 (0x4L): item.fileContent
        flag 4 (0x5L): board.userProfileUrl
        flag 5 (0x6L): board.userName
        flag 6 (0x7L): board.boardType
        flag 7 (0x8L): board.likeCount
        flag 8 (0x9L): board.commentCount
        flag 9 (0xaL): board.firstDate
        flag 10 (0xbL): null
    flag mapping end*/
    //end
}