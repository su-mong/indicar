package com.iindicar.indicar.b2_community.boardDetail;

import android.content.Intent;
import android.widget.EditText;

import com.iindicar.indicar.data.vo.BoardCommentVO;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;

import java.util.List;

/**
 * Created by yeseul on 2018-04-25.
 */

public interface BoardDetailNavigator {

    void onStartBoardUpdated();

    void onFinishActivity();

    void onHeaderAdded(BoardDetailVO header);

    void onItemsAdded(List<BoardFileVO> items);

    void onItemAdded(BoardFileVO file);

    Intent getActivityIntent();

    void showKeyboard();

    void hideKeyboard();

    void onBoardNotAvailable();

    void onCommentUpdated(List<BoardCommentVO> list);

    void onCommentProfileAttached(BoardCommentVO comment, UserVO vo);

    void showTestToast(String message);

}
