package com.iindicar.indicar.b1_tunning;

import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;

import java.util.List;

/**
 * Created by yeseul on 2018-05-01.
 */

public interface NoticeNavigator {

    void openBoardDetail(int position);

    void showPageEndMessage();

    void onImageAttached(BoardDetailVO board, BoardFileVO vo);

    void onProfileAttached(BoardDetailVO board, UserVO vo);

    void onListAdded(List<BoardDetailVO> list);

}
