package com.example.xiaojun.lingdaotanchuang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/7/13.
 */

public class SlowScrollView extends ScrollView {
    public SlowScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public SlowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SlowScrollView(Context context) {
        super(context);
    }
    @Override
    public void fling(int velocityY) {
//此处改变速度，可根据需要变快或变慢。
        super.fling(velocityY / 20);
    }
}
