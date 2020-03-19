package com.example.module_base.loadview;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2020/3/2 12:57
 * @Description：描述信息
 */

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_base.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * 自定义要切换的布局，通过IVaryViewHelper实现真正的切换<br>
 * 使用者可以根据自己的需求，使用自己定义的布局样式
 */
public class LoadHelpView {

    private IReplaceViewHelper helper;
    private AnimationDrawable animationDrawable;

    public LoadHelpView(View view) {
        this(new ReplaceViewHelper(view));
    }

    public LoadHelpView(IReplaceViewHelper helper) {
        super();
        this.helper = helper;
    }

    // 数据异常
    public void showError(String errorText, String buttonText, int picResId, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.load_error);
        // 设置图片
        ImageView mIvShowPic = (ImageView) layout.findViewById(R.id.mIvShowPic);
        mIvShowPic.setBackgroundResource(picResId);
        // 设置提示文字
        TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
        mTvTip.setText(errorText);

        // 设置按钮
        TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
        mTvBtn.setText(buttonText);
        mTvBtn.setOnClickListener(onClickListener);

        helper.showLayout(layout);
    }

    // 数据异常简易版
    public void showError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.load_error);
        TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
        mTvBtn.setOnClickListener(onClickListener);
        helper.showLayout(layout);
    }


    // 空数据
    public void showEmpty(String errorText, String buttonText, int picResId, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.load_empty);

        ImageView mIvShowPic = (ImageView) layout.findViewById(R.id.mIvShowPic);
        mIvShowPic.setBackgroundResource(picResId);

        TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
        mTvTip.setText(errorText);

        TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
        mTvBtn.setText(buttonText);
        mTvBtn.setOnClickListener(onClickListener);

        helper.showLayout(layout);
    }

    // 空数据简易版
    public void showEmpty(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.load_empty);
        TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
        mTvBtn.setOnClickListener(onClickListener);
        helper.showLayout(layout);
    }

    // 正在加载
    public void showLoading(String loadText) {
        View layout = helper.inflate(R.layout.load_ing);
        TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
        GifImageView mIvAnim =  layout.findViewById(R.id.mIvAnim);
        GifDrawable gifDrawable = (GifDrawable) mIvAnim.getDrawable();
        gifDrawable.start(); //开始播放
     //   Glide.with(context).load(R.drawable.jjz).into(mIvAnim);
    /*    animationDrawable = (AnimationDrawable) mIvAnim.getDrawable();
        animationDrawable.start(); // 开启帧动画*/
        mTvTip.setText(loadText);
        helper.showLayout(layout);
    }

    public void dismiss() {
        helper.dismissView();
    }
}
