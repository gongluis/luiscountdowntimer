package com.cardinfolink.luiscountdowntimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * author : luis
 * e-mail : luis.gong@cardinfolink.com
 * date   : 2020/7/17  16:31
 * desc   :
 */
public class LuisCountDownTimer extends LinearLayout {

    private static final String TAG = LuisCountDownTimer.class.getSimpleName();
    private TextView mHourOneTv;
    private TextView mHourTwoTv;
    private TextView mMinOneTv;
    private TextView mMinTwoTv;
    private TextView mSecOneTv;
    private TextView mSecTwoTv;
    private CountDownTimer timer;

    public LuisCountDownTimer(Context context) {
        super(context);
        initView(context);
    }

    public LuisCountDownTimer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.count_layout, this);
        mHourOneTv = this.findViewById(R.id.tv_hour_one);
        mHourTwoTv = this.findViewById(R.id.tv_hour_two);

        mMinOneTv = this.findViewById(R.id.tv_min_one);
        mMinTwoTv = this.findViewById(R.id.tv_min_two);

        mSecOneTv = this.findViewById(R.id.tv_sec_one);
        mSecTwoTv = this.findViewById(R.id.tv_sec_two);
    }

    public void start(long millisInFuture, OnFinishCallBack callBack) {
        //开始倒计时
        getCountDownTime(millisInFuture, callBack);
    }

    private void getCountDownTime(long millisInFuture, final OnFinishCallBack callBack) {

        timer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long l) {//before you finish your activity or fragment, you must use CountDownTimer.cancel() to cancel this CountDownTimer, or you will get a NullPointException

                long day = l / (1000 * 24 * 60 * 60); //单位天
                long hour = (l - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60); //单位时
                long minute = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60); //单位分
                long second = (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;//单位秒

                if (minute >= 10) {
                    String min = String.valueOf(minute);
                    mMinOneTv.setText(min.substring(0, 1));
                    mMinTwoTv.setText(min.substring(1, 2));
                } else {
                    mMinOneTv.setText("0");
                    mMinTwoTv.setText(String.valueOf(minute));
                }
                if (second >= 10) {
                    String sec = String.valueOf(second);
                    mSecOneTv.setText(sec.substring(0, 1));
                    mSecTwoTv.setText(sec.substring(1, 2));
                } else {
                    mSecOneTv.setText("0");
                    mSecTwoTv.setText(String.valueOf(second));
                }

            }

            @Override
            public void onFinish() {
                callBack.onCountDownFinish();
                cancel();
            }
        };
        timer.start();
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public interface OnFinishCallBack {
        void onCountDownFinish();
    }
}
