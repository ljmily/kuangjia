package com.example.module_base.ext

import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.module_base.data.BaseResp
import com.example.module_base.rx.BaseSubscriber
import com.example.module_base.rx.DefaultTextWatcher
import com.example.module_base.rx.BaseFunc
import com.yunwei.base.rx.BaseFuncBoolean
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers





/**
 * Created by Xing
 * on 2018/7/16.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

private var mLastClickTime: Long = 0
val TIME_INTERVAL = 1000L
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener {
        val nowTime = System.currentTimeMillis()
        if (nowTime - mLastClickTime > TIME_INTERVAL) {
            // do something
            mLastClickTime = nowTime
            method()
        } else {
            //Toast.makeText(this@MainActivity, "不要重复点击", Toast.LENGTH_SHORT).show()
        }
    }
}

fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun Button.enable(editText: EditText, method: () -> Boolean) {
    val button = this
    editText.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            button.isEnabled = method()
        }
    })
}

fun EditText.showPwd() {
    this.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
}

fun EditText.hidePwd() {
    this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
}

fun TextView.show() {
    this.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
}

fun TextView.hide() {
    this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

