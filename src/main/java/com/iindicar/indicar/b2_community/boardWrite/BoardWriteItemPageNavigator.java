package com.iindicar.indicar.b2_community.boardWrite;

/**
 * Created by yeseul on 2018-05-06.
 */

public interface BoardWriteItemPageNavigator {

    void pageChangeToPosition(int position);

    void addPage(int position);

    void removePage(int position);
}
