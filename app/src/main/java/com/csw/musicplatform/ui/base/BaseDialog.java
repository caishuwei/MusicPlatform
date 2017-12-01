package com.csw.musicplatform.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csw.musicplatform.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 对话框基类
 * Created by caisw on 2017/12/1.
 */
public abstract class BaseDialog extends Dialog implements IViewCreator {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.fl_custom_view_container)
    FrameLayout fl_custom_view_container;
    @BindView(R.id.ll_bottom_button_container)
    LinearLayout ll_bottom_button_container;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.DialogTheme);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_base);
        //设置窗口透明度为完全透明（必须在设置布局之后）
        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.dimAmount = 0;
        getWindow().setAttributes(wlp);

        fl_custom_view_container = findViewById(R.id.fl_custom_view_container);
        LayoutInflater.from(getContext()).inflate(getContentViewId(), fl_custom_view_container, true);
        ButterKnife.bind(this);
        onViewInit();
        initAdapter();
        initListener();
        onViewInitFinish();
    }

    @Override
    public void onViewInit() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onViewInitFinish() {

    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void addBottomButton(String buttonName, View.OnClickListener listener) {
        View view = createBottomButton(buttonName, listener);
        ll_bottom_button_container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private View createBottomButton(String buttonName, View.OnClickListener listener) {
        AppCompatButton button = (AppCompatButton) LayoutInflater.from(getContext()).inflate(R.layout.view_dialog_bottom_button, ll_bottom_button_container, false);
        button.setText(buttonName);
        button.setOnClickListener(listener);
        button.setGravity(Gravity.CENTER);
        button.setTextColor(Color.BLACK);
        return button;
    }
}
