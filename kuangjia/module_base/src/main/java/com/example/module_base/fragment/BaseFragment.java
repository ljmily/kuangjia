package com.example.module_base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.module_base.data.UserData;
import com.example.module_base.receiver.NetBroadcastReceiver;
import com.example.module_base.utils.PreferenceUtil;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2020/3/9 10:43
 * @Description：描述信息
 */
public abstract class BaseFragment extends RxFragment implements NetBroadcastReceiver.NetChangeListener {
    protected View rootView;
    private boolean mIsLoadedData;
    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化netEvent
        netEvent = this;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化方法
        initView();
    }

    //获取用户信息
    @Nullable
    public final UserData getUser() {
        return (UserData) PreferenceUtil.INSTANCE.find("sp_user", UserData.class);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isResumed()) {
            this.handleOnVisibilityChangedToUser(isVisibleToUser);
        }

    }

    public void onResume() {
        super.onResume();
        if (this.getUserVisibleHint()) {
            this.handleOnVisibilityChangedToUser(true);
        }

    }

    public void onPause() {
        super.onPause();
        if (this.getUserVisibleHint()) {
            this.handleOnVisibilityChangedToUser(false);
        }

    }

    /**
     * 处理对用户是否可见
     *
     * @param isVisibleToUser 可见
     */
    private final void handleOnVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // 对用户可见
            if (!this.mIsLoadedData) {
                this.mIsLoadedData = true;
                this.onLazyLoadOnce();
            }

            this.onVisibleToUser();
        } else {
            // 对用户不可见
            this.onInvisibleToUser();
        }

    }

    /**
     * 懒加载一次。如果只想在对用户可见时才加载数据，并且只加载一次数据，在子类中重写该方法
     */
    protected void onLazyLoadOnce() {
    }

    /**
     * 对用户可见时触发该方法。如果只想在对用户可见时才加载数据，在子类中重写该方法
     */
    protected void onVisibleToUser() {
    }

    /**
     * 对用户不可见时触发该方法
     */
    protected void onInvisibleToUser() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        netEvent = null;
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {
    }
}