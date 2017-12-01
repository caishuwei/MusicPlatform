package com.csw.musicplatform.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * recyclerView間隔線
 * Created by caisw on 2017/10/18.
 */
public class SpaceLineDecoration extends RecyclerView.ItemDecoration {
    private int l;
    private int t;
    private int r;
    private int b;
    private int color;

    public static SpaceLineDecoration getInstanceByDp(int l, int t, int r, int b, int color) {
        return new SpaceLineDecoration(
                ScreenInfo.dip2px(l),
                ScreenInfo.dip2px(t),
                ScreenInfo.dip2px(r),
                ScreenInfo.dip2px(b),
                color
        );
    }

    public SpaceLineDecoration(int l, int t, int r, int b, int color) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        this.color = color;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(l, t, r, b);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        View child;
        Rect rect = new Rect();
        for (int i = 0; i < childCount; i++) {
            child = parent.getChildAt(i);
            rect.set(child.getLeft() - l, child.getTop() - t, child.getRight() + r, child.getBottom() + b);
            if (l > 0) {
                c.save();
                c.clipRect(rect.left, rect.top, rect.left + l, rect.bottom);
                c.drawColor(color);
                c.restore();
            }
            if (t > 0) {
                c.save();
                c.clipRect(rect.left, rect.top, rect.right, rect.top + t);
                c.drawColor(color);
                c.restore();
            }
            if (r > 0) {
                c.save();
                c.clipRect(rect.right - r, rect.top, rect.right, rect.bottom);
                c.drawColor(color);
                c.restore();
            }
            if (b > 0) {
                c.save();
                c.clipRect(rect.left, rect.bottom - b, rect.right, rect.bottom);
                c.drawColor(color);
                c.restore();
            }
        }

    }
}
