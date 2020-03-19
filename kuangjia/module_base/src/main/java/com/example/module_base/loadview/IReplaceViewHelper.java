package com.example.module_base.loadview;

import android.content.Context;
import android.view.View;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2020/3/2 12:57
 * @Description：描述信息
 */
public interface IReplaceViewHelper {

    public abstract View getCurrentLayout();

    public abstract void dismissView();

    public abstract void showLayout(View view);

    public abstract void showLayout(int layoutId);

    public abstract View inflate(int layoutId);

    public abstract Context getContext();

    public abstract View getView();

}
