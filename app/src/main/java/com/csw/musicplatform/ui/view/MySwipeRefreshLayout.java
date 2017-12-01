package com.csw.musicplatform.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.csw.musicplatform.R;

/**
 * 优化滑动判断
 * <P>Created by Caisw on 2017/5/4.</P>
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private float startX;
    private float startY;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark);
        setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://手指接触屏幕
                startX = ev.getX();//记录起始点坐标
                startY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE://手指移动
                float currentX = ev.getX();//记录当前手指所在点坐标
                float currentY = ev.getY();
                //竖直方向滑动且方向向下，由父类判断是否拦截，其他情况不拦截
                if (Math.abs(currentY - startY) > Math.abs(currentX - startX) && currentY > startY) {
                    return super.onInterceptTouchEvent(ev);
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
