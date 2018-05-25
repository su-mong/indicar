package com.iindicar.indicar.b2_community.boardList;

import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;

import java.util.List;

/**
 * Created by yeseul on 2018-04-25.
 */

public interface BoardListNavigator {

    void openBoardDetail(int position);

    void showPageEndMessage();

    void onImageAttached(BoardDetailVO board, BoardFileVO vo);

void onSearch(String searchWord);

    void onProfileAttached(BoardDetailVO board, UserVO vo);

    void onListAdded(List<BoardDetailVO> list);

}
