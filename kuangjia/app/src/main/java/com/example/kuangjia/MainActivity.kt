package com.example.kuangjia


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.example.kuangjia.injection.component.DaggerUserComponent
import com.example.kuangjia.injection.module.UserModule
import com.example.module_base.activity.BaseMvpActivity
import com.example.module_base.common.RouterPath
import com.example.module_base.common.RouterPath.UserCenter.Companion.path_login
import com.example.module_base.data.UserData
import com.example.module_base.ext.onClick
import com.example.module_base.utils.AlertDialogios
import com.example.module_base.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseMvpActivity<BannerPresenter>(),HomeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);
    }

    override fun initView() {
        button.onClick {
            ARouter.getInstance().build(path_login).navigation()
        }
        //  mPresenter.ckrw("18801424016","Nir4PHR715WK2iZyA6RVsQ==")
        var a  = 0
        var c  = 0
        val VIDEO_PERMISSIONS_CODE = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val arrayOf = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            var array = arrayOf
            for (promiss in arrayOf){
                if (checkSelfPermission(promiss) == PackageManager.PERMISSION_DENIED){
                    array[c] = promiss
                    c++
                }else{
                    a++
                }
            }
            if (a==arrayOf.size){
                Toast.makeText(this@MainActivity, "全部申请", Toast.LENGTH_SHORT).show()
                a==0
            }else{
                this.requestPermissions(array, VIDEO_PERMISSIONS_CODE);
            }
        }else{
            Toast.makeText(this@MainActivity, "6.0以下可以用", Toast.LENGTH_SHORT).show()

        }


        // 创建Fragment对象
        var fragment1 = MeFragment()
        // 得到FragmentManager
        val transaction =getFragmentManager().beginTransaction()
        // 添加Fragment对象并提交
        transaction.add(R.id.frame,fragment1).show(fragment1).commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //定义变量，如果有多个权限的情况下，只弹出一个提示框
        var b = true
        for (permission: String in  permissions) {
            //判断当前权限是否开启
            val b1 = this.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED;
            //为true没开启  为false开启了
            if (b1){
                //如果返回true表示用户点了禁止获取权限，但没有勾选不再提示。
                //返回false表示用户点了禁止获取权限，并勾选不再提示。
                //我们可以通过该方法判断是否要继续申请权限
                var shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(this,permission);
                if (shouldShow){
                    Toast.makeText(this,"可以重新申请"+permission,Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"跳转设置页面"+permission,Toast.LENGTH_SHORT).show()
                    if (b){
                        b=false
                        AlertDialogios(this).builder().setMsg("权限被拒绝，导致部分功能将无法使用\n请前往设置页面开启权限").setTitle("提示").setNegativeButton("取消") {
                            AlertDialogios(this).dismiss()
                        }.setPositiveButton("前往设置") {
                            AlertDialogios(this).dismiss()
                            //跳转到应用设置中
                            var intent =  Intent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS";
                                intent.setData(Uri.fromParts("package", packageName, null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                intent.action = Intent.ACTION_VIEW;
                                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                                intent.putExtra("com.android.settings.ApplicationPkgName", packageName);
                            }
                            startActivity(intent);
                        }.show()
                    }

                }
                Toast.makeText(this,"$b1=====没开启"+permission,Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"$b1=====开启了"+permission,Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onNetChange(netWorkState: Boolean) {
       /* if (netWorkState){
            Toast.makeText(this,"有网", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"没网", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun ckrw1(result: UserData) {
        Log.e("aaaa",result.toString())
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }



}

