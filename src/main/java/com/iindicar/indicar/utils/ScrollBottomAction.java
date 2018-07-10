package com.iindicar.indicar.utils;

import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by yeseul on 2018-04-01.
 */

public class ScrollBottomAction implements ViewTreeObserver.OnScrollChangedListener, View.OnTouchListener{

    NestedScrollView scrollView;
    boolean isReady;
    List<onScrollEndListener> scrollBottomListenerList;

    public ScrollBottomAction(){
        this.isReady = false;
        this.scrollBottomListenerList = new ArrayList<>();
    }

    public ScrollBottomAction with(NestedScrollView scrollView){
        this.scrollView = scrollView;
        // 스크롤 리스너 등록
        this.scrollView.getViewTreeObserver().addOnScrollChangedListener(this);
        // 터치 리스너 등록
        this.scrollView.setOnTouchListener(this);

        return this;
    }

    /**
     * 외부 이벤트 등록
     * */
    public ScrollBottomAction setOnScrollBottomListener(onScrollEndListener listener){
        if(this.scrollBottomListenerList.contains(listener) == false){
            this.scrollBottomListenerList.add(listener);
        }
        return this;
    }

    /**
    * 외부 이벤트 제거
     * */
    public void removeOnScrollBottomListener(onScrollEndListener listener){
        if(this.scrollBottomListenerList.contains(listener)){
            this.scrollBottomListenerList.remove(listener);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        if(action == ACTION_UP) { //아래로 스크롤
            if(this.isReady == true && this.scrollBottomListenerList.size() > 0){
                for(onScrollEndListener item : this.scrollBottomListenerList){
                    item.onScrollBottom(this.scrollView);
                }
            }
            this.isReady = false;
        } else if(action == ACTION_MOVE){
            if(isReady == false && this.isScrollBottom() == true){
                isReady = true;
            }
        }

        return false;
    }

    @Override
    public void onScrollChanged(){
        isReady = this.isScrollBottom();
    }

    private boolean isScrollBottom(){
        return !scrollView.canScrollVertically(1);
    }

    public interface onScrollEndListener {
        void onScrollBottom(ScrollingView view);
    }

}
