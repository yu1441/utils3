package com.yujing.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;

/**
 * 半透明等待对话框，单例模式
 *
 * @author 余静 2018年11月20日17:00:27
 */
@SuppressWarnings({"unused"})
public class YShow extends Dialog {
    private Activity activity;
    private ProgressBar mProgressBar;
    private String message1;
    private String message2;
    private TextView textView1;
    private TextView textView2;
    private boolean isCancelable = true;
    @SuppressLint("StaticFieldLeak")
    private static YShow yDialog = null;

    // 构造函数，主题android.R.style.Theme_Holo_Light_Dialog_NoActionBar,Theme_DeviceDefault_Dialog_NoActionBar,Theme_Material_Dialog_NoActionBar
    private YShow(Activity activity, String text, String text2, boolean isCancelable) {
        super(activity, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        this.message1 = text;
        this.message2 = text2;
        this.isCancelable = isCancelable;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = getView();
        setContentView(linearLayout);// 设置布局view
        Window window = getWindow();
        if (window != null)
            window.setGravity(Gravity.CENTER);// 设置Gravity居中
        //设置 window的Background为圆角
        GradientDrawable gradientDrawable = new GradientDrawable();
        int strokeWidth = 1; // 1dp 边框宽度
        int roundRadius = 5; // 6dp 圆角半径
        int strokeColor = Color.parseColor("#303A89FF");//边框颜色
        int fillColor = Color.parseColor("#A0000000");//内部填充颜色
        gradientDrawable.setColor(fillColor);
        gradientDrawable.setCornerRadius(dip2px(getContext(), roundRadius));
        gradientDrawable.setStroke(dip2px(getContext(), strokeWidth), strokeColor);
        if (window != null) {
            window.setBackgroundDrawable(gradientDrawable);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = 0.9f;// 透明度
            lp.dimAmount = 0f;// 模糊度
            //lp.width=dip2px(getContext(), 90);
            window.setAttributes(lp);
        }
        setCancelable(isCancelable);// 是否允许按返回键
        //找到mProgressBar
        mProgressBar = (ProgressBar) linearLayout.getChildAt(0);
        //找到textView1
        textView1 = (TextView) linearLayout.getChildAt(1);
        //找到textView2
        textView2 = (TextView) linearLayout.getChildAt(2);
        setMessage1(message1);
        setMessage2(message2);
        setCanceledOnTouchOutside(false);// 触摸屏幕其他区域不关闭对话框

    }

    //获取布局
    private LinearLayout getView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.removeAllViews();
        linearLayout.setOrientation(LinearLayout.VERTICAL);//设置纵向布局
        linearLayout.setPadding(dip2px(getContext(), 10), dip2px(getContext(), 15), dip2px(getContext(), 10), dip2px(getContext(), 10));
        linearLayout.setMinimumHeight(dip2px(getContext(), 90));//最小高度
        linearLayout.setMinimumWidth(dip2px(getContext(), 90));//最小宽度

        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageViewLayoutParams.gravity = Gravity.CENTER;//设置中心对其
        //实例化一个ProgressBar
        ProgressBar mProgressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setLayoutParams(imageViewLayoutParams);

        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvParams.setMargins(0, 0, 0, 0);
        tvParams.gravity = Gravity.CENTER;//设置中心
        //实例化一个TextView
        TextView tv = new TextView(getContext());
        tv.setLayoutParams(tvParams);
        tv.setTextSize(12);
        tv.setTextColor(Color.parseColor("#EEEEEE"));
        tv.setText(message1);

        LinearLayout.LayoutParams tvParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvParams2.setMargins(0, dip2px(getContext(), 2), 0, 0);
        tvParams2.gravity = Gravity.CENTER;//设置中心
        //实例化第二个TextView
        TextView tv2 = new TextView(getContext());
        tv2.setLayoutParams(tvParams2);
        tv2.setTextSize(10);
        tv2.setTextColor(Color.parseColor("#EEEEEE"));
        tv2.setText(message2);

        linearLayout.addView(mProgressBar);
        linearLayout.addView(tv);
        linearLayout.addView(tv2);
        return linearLayout;
    }

    public void setMessage1(String message) {
        this.message1 = message;
        if (textView1 != null) {
            textView1.setText(this.message1);
            if (message1 == null || message1.isEmpty()) {
                textView1.setVisibility(View.GONE);// 没有值就隐藏textView
            } else {
                textView1.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setMessage2(String message) {
        this.message2 = message;
        if (textView2 != null) {
            textView2.setText(this.message2);
            if (message2 == null || message2.isEmpty()) {
                textView2.setVisibility(View.GONE);
            } else {
                textView2.setVisibility(View.VISIBLE);
            }
        }
    }

    public String getMessage1() {
        return message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setProgressBarColor(@ColorInt int color) {
        if (mProgressBar != null) {
            mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
    }

    public synchronized static void show(Activity activity) {
        show(activity, null, null, true);
    }

    public synchronized static void show(Activity activity, String message) {
        show(activity, message, null, true);
    }

    public synchronized static void show(Activity activity, String message, boolean isCancelable) {
        show(activity, message, null, isCancelable);
    }

    public synchronized static void show(Activity activity, String message1, String message2, boolean isCancelable) {
        finish();
        if (activity == null || activity.isFinishing())
            return;
        yDialog = new YShow(activity, message1, message2, isCancelable);
        yDialog.show();
    }

    public synchronized static void showUpdate(Activity activity) {
        showUpdate(activity, null, null, true);
    }

    public synchronized static void showUpdate(Activity activity, String message) {
        showUpdate(activity, message, null, true);
    }

    public synchronized static void showUpdate(Activity activity, String message, boolean isCancelable) {
        showUpdate(activity, message, null, isCancelable);
    }

    public synchronized static void showUpdate(Activity activity, String message1, String message2, boolean isCancelable) {
        if (isShow()) {
            setMessage(message1);
            setMessageOther(message2);
        } else {
            show(activity, message1, message2, isCancelable);
        }
    }

    //设置文本
    public synchronized static void setMessage(String message) {
        if (yDialog != null) {
            yDialog.setMessage1(message);
        }
    }

    public synchronized static void setMessageOther(String message) {
        if (yDialog != null) {
            yDialog.setMessage2(message);
        }
    }

    public synchronized static void setColor(@ColorInt int color) {
        if (yDialog != null) {
            yDialog.setProgressBarColor(color);
        }
    }

    public synchronized static void setCancel(boolean isCancelable) {
        if (yDialog != null) {
            yDialog.setCancelable(isCancelable);// 是否允许按返回键
        }
    }

    /**
     * 获取Dialog
     *
     * @return YShow
     */
    public static YShow getDialog() {
        return yDialog;
    }

    /**
     * 是否正在显示
     *
     * @return 是否正在显示
     */
    public synchronized static boolean isShow() {
        if (yDialog != null) {
            return yDialog.isShowing();
        }
        return false;
    }

    /**
     * 关闭对话框
     */
    public static void finish() {
        if (yDialog != null) {
            yDialog.dismiss();
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
