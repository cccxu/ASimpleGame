package cn.cccxu.ASimpleGame.utils;

import android.content.Context;
import android.util.TypedValue;

public class SizeUtils {
    public static int dp2Px(Context context, int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics());
    }
}
