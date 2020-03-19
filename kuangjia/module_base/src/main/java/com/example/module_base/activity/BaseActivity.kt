package com.example.module_base.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.module_base.R
import com.example.module_base.common.BaseConstant.KEY_SP_USER
import com.example.module_base.data.UserData
import com.example.module_base.fragment.BaseFragment.netEvent
import com.example.module_base.receiver.NetBroadcastReceiver
import com.example.module_base.utils.ActivityUtil
import com.example.module_base.utils.ConstantUtil
import com.example.module_base.utils.PreferenceUtil
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by Xing
on 2018/7/14.
 */
abstract class BaseActivity : RxAppCompatActivity(), NetBroadcastReceiver.NetChangeListener {

    protected var isNeedhide = true

    val TAG = javaClass.simpleName

    var netBroadcastReceiver: NetBroadcastReceiver? = null
    private val isOpenKeyboardEvent = true
    private var isRegistered = false
    private var LOAD_DIALOG: ProgressDialog? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!initArgs(intent.extras)) {
            finish()
        }
        // 添加到Activity工具类
        ActivityUtil.getInstance().addActivity(this)
        // 初始化netEvent
        netEvent = this
        //实例化IntentFilter对象
        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        netBroadcastReceiver = NetBroadcastReceiver()
        registerReceiver(netBroadcastReceiver, filter)
        isRegistered = true
        initDialog()
    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        //  StatusBarCompat.setStatusBarColor(this, resources.getColor(R.color.colorAccent), true)
//        StatusBarCompat.resetActionBarContainerTopMargin(getWindow(), android.support.v7.appcompat.R.id.action_bar_container)

        initBus()
        initView()
    }

    override fun onResume() {
        super.onResume()
        val resources = this.resources
        val configuration = resources.configuration
        configuration.fontScale = ConstantUtil.TEXTVIEWSIZE.toFloat()
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun initDialog() {
        LOAD_DIALOG = ProgressDialog(this)
        LOAD_DIALOG!!.setMessage("loading...")
        LOAD_DIALOG!!.setCanceledOnTouchOutside(false)
    }

    override fun onStart() {
        super.onStart()
        foregroundActivity = this
    }

    private fun initBus() {
/*        Bus.observe<FailureLoginEvent>().subscribe {
            if (getUser() != null) {
                DialogUtils.showDialogCallbackConfirmOnly(this@BaseActivity, getString(R.string.tip), it.msg, getString(R.string.login_again), object : DialogUtils.DialogCallback {
                    override fun confirm() {
                        Account.putUserInfo(null)
                        JMessageClient.logout()
                        AppManager.instance.finishAllActivity()
                        ARouter.getInstance().build("/userCenter/LoginActivity").navigation()
                    }
                })
            }
        }.registerInBus(this)*/
    }

    abstract fun initView()
    override fun onDestroy() { // Activity销毁时，提示系统回收
        System.gc();
        netEvent = null
        //解绑
        if (isRegistered) {
            unregisterReceiver(netBroadcastReceiver)
        }
        // 移除Activity
        ActivityUtil.getInstance().removeActivity(this)
        super.onDestroy()
    }

    fun getUser(): UserData? {
        return PreferenceUtil.find(KEY_SP_USER, UserData::class.java)
    }
    /*fun getUser(): SharedPreferencesUtils {
        return preferences
    }*/

    open fun initArgs(bundle: Bundle?): Boolean {
        return true
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.side_out_left)
    }

    /**
     * 带动画的启动activity
     */
    fun startActivityWithAnimation(intent: Intent) {
        startActivity(intent)
    }

    /**
     * 带动画的启动activity
     */
    fun startActivityForResultWithAnimation(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.slide_in_right, R.anim.side_out_left)
    }

    /**
     * 处理软件盘智能弹出和隐藏
     */
    /*override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                val view = currentFocus
                if (isNeedhide) {
                    KeyboardUtils.editKeyboard(ev, view, this)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }*/
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean { // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 移除Activity
            ActivityUtil.getInstance().removeActivity(this)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    fun hasPermission(vararg permissions: String?): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    override fun onNetChange(netWorkState: Boolean) {}

    // 修改状态栏的颜色
    fun setStatusBarColor(color: Int) {
        try {
            val window = window
            // 取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            }
            // 设置状态栏颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = resources.getColor(color)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 获取状态栏的高度
    val statusBarHeight: Int
        get() {
            val resources = resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
        }

    // 底部导航栏的高度
    val navigationBarHeight: Int
        get() {
            val resources = resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
        }

    // 判断底部导航栏是否显示
    val isNavigationBarShow: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = windowManager.defaultDisplay
            val size = Point()
            val realSize = Point()
            display.getSize(size)
            display.getRealSize(realSize)
            realSize.y != size.y
        } else {
            val menu = ViewConfiguration.get(this).hasPermanentMenuKey()
            val back =
                KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            !menu && !back
        }

    // 事件分发
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (isOpenKeyboardEvent) {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                val v = currentFocus
                if (isShouldHideInput(v, ev)) {
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm?.hideSoftInputFromWindow(v!!.windowToken, 0)
                }
                return super.dispatchTouchEvent(ev)
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            window.superDispatchTouchEvent(ev) || onTouchEvent(ev)
        } else {
            super.dispatchTouchEvent(ev)
        }
    }

    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return (event.x <= left || event.x >= right
                    || event.y <= top || event.y >= bottom)
        }
        return false
    }

    companion object {
        // 网络状态改变监听事件
        @JvmField
        var netEvent: NetBroadcastReceiver.NetChangeListener? = null
        /**
         * 获取当前处于前台的activity
         */
        lateinit var foregroundActivity: BaseActivity
    }
}