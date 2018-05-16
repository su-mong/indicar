package com.iindicar.indicar.b2_community.boardDetail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.commit451.teleprinter.Teleprinter;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditActivity;
import com.iindicar.indicar.data.vo.BoardCommentVO;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.data.vo.WriteBoardVO;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.databinding.BoardDetailActivityBinding;
import com.iindicar.indicar.utils.DialogUtil;
import com.iindicar.indicar.utils.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.iindicar.indicar.Constant.RequestCode.REQUEST_BOARD_UPDATE;

/**
 * 게시물 조회 화면
 *
 * TODO
 * 3. 맨위로 스크롤
 *
 * 7. 이미지 슬라이드 뷰
 *
 */

public class BoardDetailActivity extends BaseActivity<BoardDetailActivityBinding> implements BoardDetailNavigator {

    private final String TAG = this.getClass().getSimpleName();

    private BoardDetailViewModel viewModel = new BoardDetailViewModel();

    private BoardDetailAdapter boardAdapter;
    private BoardCommentAdapter commentAdapter;
    private boolean isUpdated=false;

    private Teleprinter keyboard;

    private Boolean canUpdate;

    @Override
    public void onBackPressed() {
        if(viewModel.isKeyboardOpen.get()){
            hideKeyboard();
        } else {
            onFinishActivity();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.board_detail_activity;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_community);
        leftImageId.set(R.drawable.btn_back);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.canUpdate = intent.getBooleanExtra("canUpdate", false);

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFinishActivity();
            }
        });

        binding.setViewModel(viewModel);
        viewModel.setNavigator(this);

        initBoardView();
        initCommentView();

        onHeaderAdded((BoardDetailVO) intent.getParcelableExtra("boardVO"));

        viewModel.start();

        // bind edit text
        binding.boardContent.commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.commentWrite.set(editable.toString());
            }
        });

        binding.boardContent.commentText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) {
                    keyboard.showKeyboard(view);
                }
                else {
                    keyboard.hideKeyboard();
                }
            }
        });

        // bind scroll listener to show/hide buttons
        binding.boardContent.scrollViewContainer.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                boolean isPageDown = (oldScrollY - scrollY) > 20; // scroll up
                boolean isPageUp = (scrollY - oldScrollY) > 20; // scroll down

                Handler handler = new Handler();

                if(isPageUp){
                    viewModel.isPageUpScrolling.set(true);
                    viewModel.isScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isScrolling.set(false);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isPageUpScrolling.set(false);
                        }
                    }, 2000);
                }

                if(isPageDown){
                    viewModel.isScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isScrolling.set(false);
                        }
                    }, 1000);
                }

            }
        });

        binding.boardContent.scrollViewContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(viewModel.isKeyboardOpen.get()){
                    keyboard.hideKeyboard();
                }
                return false;
            }
        });

        ((SimpleItemAnimator)binding.boardContent.recyclerviewBoardContainer.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void initBoardView() {
        boardAdapter = new BoardDetailAdapter(this);
        boardAdapter.setOnMenuClickListener(new BoardDetailAdapter.OnMenuClickListener() {
            @Override
            public void onMenuClick(View view, int position) {
                showMenuDialog(view);
            }
        });
        binding.boardContent.recyclerviewBoardContainer.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.boardContent.recyclerviewBoardContainer.setAdapter(boardAdapter);
        binding.boardContent.recyclerviewBoardContainer.setNestedScrollingEnabled(false);
    }

    private void initCommentView() {
        commentAdapter = new BoardCommentAdapter(this);
        commentAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showCommentDialog(position);
            }
        });
        binding.boardContent.recyclerviewCommentContainer.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        binding.boardContent.recyclerviewCommentContainer.setAdapter(commentAdapter);
        binding.boardContent.recyclerviewCommentContainer.setNestedScrollingEnabled(false);

        RecyclerViewDecoration decoration = new RecyclerViewDecoration(0, 3, 0, 0);
        binding.boardContent.recyclerviewCommentContainer.addItemDecoration(decoration);

        initCommentKeyboard();
    }

    private void initCommentKeyboard() {
        keyboard = new Teleprinter(this);
        keyboard.addKeyboardToggleListener(new Teleprinter.OnKeyboardToggleListener() {
            @Override
            public void onKeyboardShown(int keyboardSize) {
                binding.boardContent.scrollViewContainer.fullScroll(View.FOCUS_DOWN);
                binding.boardContent.commentText.requestFocus();
                viewModel.isKeyboardOpen.set(true);
            }

            @Override
            public void onKeyboardClosed() {
                viewModel.isKeyboardOpen.set(false);
            }
        });
    }

    private void showMenuDialog(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        if(canUpdate) { // 수정 가능한 게시물인 경우
            getMenuInflater().inflate(R.menu.board_menu_canupdate, popupMenu.getMenu());
        } else {
            getMenuInflater().inflate(R.menu.board_menu, popupMenu.getMenu());
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case R.id.update:
                        onStartBoardUpdated();
                        break;
                    case R.id.delete:
                        showDeleteDialog();
                        break;
                    case R.id.report:
                        Toast.makeText(BoardDetailActivity.this,
                                "BoardDetail file url: " + boardAdapter.getItemList().get(0).getFileUrl()
                                , Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void showDeleteDialog() {

        DialogUtil.showDialog(this,
                R.drawable.button_trash,
                "이 게시글을 정말로 삭제하시겠습니까?",
                "Delete this post",
                0.9, 0.25,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.deleteBoard();
                    }
                });
    }

    public void showCommentDialog(final int position){
        BoardCommentVO vo = commentAdapter.getItem(position);

        if (viewModel.loginName.equals(vo.getUserName())){
            CharSequence[] items = {"댓글 수정", "댓글 삭제"};

            new AlertDialog.Builder(this)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
                                showKeyboard();
                                viewModel.startCommentUpdating(commentAdapter.getItem(position));
                            } else {
                                viewModel.deleteComment(commentAdapter.getItem(position).getCommentIndex());
                                commentAdapter.removeItem(position);
                            }
                        }
                    })
                    .show();
        } else {
            CharSequence[] items = {"부적절한 댓글 신고"};

            new AlertDialog.Builder(this)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        }
    }

    @Override
    public Intent getActivityIntent() {
        return getIntent();
    }

    @Override
    public void onStartBoardUpdated() {

        Intent intent = new Intent(getApplicationContext(), BoardWriteEditActivity.class);
        Bundle bundle = new Bundle();

        WriteBoardVO updateBoard = new WriteBoardVO();
        updateBoard.setUserId(viewModel.loginId);
        updateBoard.setUserName(viewModel.loginName);
        updateBoard.setBoardType(viewModel.boardHeader.getBoardType());
        updateBoard.setBoardId(viewModel.boardHeader.getBoardId());
        updateBoard.setFileIndex(viewModel.boardHeader.getAtchFileId());

        bundle.putParcelable(BoardWriteEditActivity.EXTRA_BOARD_DETAIL, updateBoard);

        List<BoardFileVO> boardItems = boardAdapter.getItemList();
        ArrayList<WriteFileVO> updateItems = new ArrayList<>();
        if(boardItems != null) {
            for (int i = 0; i < boardItems.size(); i++) {
                WriteFileVO vo = new WriteFileVO();
                vo.setFileIndex(boardItems.get(i).getAtchFileId());
                vo.setFilePath(boardItems.get(i).getFileUrl());
                vo.setWriteText(boardItems.get(i).getFileContent());
                updateItems.add(vo);
            }
        }
        bundle.putParcelableArrayList(BoardWriteEditActivity.EXTRA_BOARD_FILE_LIST, updateItems);

        intent.putExtra(BoardWriteEditActivity.BUNDLE_EXTRA_BOARD, bundle);
        startActivityForResult(intent, REQUEST_BOARD_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
Log.d("ddf","writeeditfinish");


        if(requestCode == REQUEST_BOARD_UPDATE){
            if(resultCode == BoardWriteEditActivity.RESULT_UPDATE_SUCCESS){
                isUpdated=true;
                boardAdapter.clearItems();
                commentAdapter.clearItems();
                viewModel.onRefreshBoard();
            }
//            if(resultCode==BoardWriteEditActivity.RESULT_UPLOAD_SUCCESS)
//                isUpdated=true;
        }
    }

    public void onDeleteBoard(){
        isUpdated=true;
        onFinishActivity();
    }

    @Override
    public void onFinishActivity() {
        Intent intent = new Intent();
        intent.putExtra("isUpdated", isUpdated);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    public void onHeaderAdded(BoardDetailVO header) {
        boardAdapter.setHeader(header);
    }

    @Override
    public void onItemsAdded(List<BoardFileVO> items) {
        boardAdapter.addItems(items);
    }

    @Override
    public void onItemAdded(BoardFileVO file) {
        boardAdapter.addItem(file);
    }

    @Override
    public void showKeyboard(){
        keyboard.showKeyboard(binding.boardContent.commentText);
        binding.boardContent.commentText.requestFocus();
    }

    @Override
    public void hideKeyboard(){
        keyboard.hideKeyboard();
        binding.boardContent.commentText.clearFocus();
    }

    @Override
    public void onBoardNotAvailable() {
        Toast.makeText(this, "존재하지 않는 게시물입니다.", Toast.LENGTH_SHORT).show();
        onFinishActivity();
    }

    @Override
    public void onCommentUpdated(List<BoardCommentVO> list) {
        LinearLayout lin_alert_empty=(LinearLayout)findViewById(R.id.lin_alert_reply_empty);
        lin_alert_empty.setVisibility(View.GONE);
        commentAdapter.updateItems(list);
Log.d("ddff","comment_updated");
    }

    @Override
    public void onCommentUpdated_emtpy() {
        LinearLayout lin_alert_empty=(LinearLayout)findViewById(R.id.lin_alert_reply_empty);
        lin_alert_empty.setVisibility(View.VISIBLE);
Log.d("ddff","empty");
    }



    @Override
    public void onCommentProfileAttached(BoardCommentVO comment, UserVO vo) {
        int position = commentAdapter.getItemList().indexOf(comment);
        comment.setUserProfileUrl(vo.getProfileImageUrl());
        commentAdapter.notifyItemChanged(position);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ddff",this.getClass().getSimpleName());
    }
}
