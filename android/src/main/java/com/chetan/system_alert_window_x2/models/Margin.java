package com.chetan.system_alert_window_x2.models;

import android.content.Context;

import com.chetan.system_alert_window_x2.utils.Commons;
import com.chetan.system_alert_window_x2.utils.NumberUtils;

public class Margin {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public Margin(Object left, Object top, Object right, Object bottom, Context context) {
        this.left = Commons.getPixelsFromDp(context, NumberUtils.getInt(left));
        this.top = Commons.getPixelsFromDp(context, NumberUtils.getInt(top));
        this.right = Commons.getPixelsFromDp(context, NumberUtils.getInt(right));
        this.bottom = Commons.getPixelsFromDp(context, NumberUtils.getInt(bottom));
    }
}
