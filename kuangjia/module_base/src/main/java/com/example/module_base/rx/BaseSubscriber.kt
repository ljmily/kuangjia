package com.example.module_base.rx

import android.util.Log
import com.example.module_base.utils.ToastUtils
import com.example.module_base.view.BaseView
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by Xing
on 2018/7/16.
 */
open class BaseSubscriber<T>(private val baseView: BaseView) : Observer<T> {

    override fun onSubscribe(d: Disposable) {
        baseView.showLoading()
    }

    override fun onError(e: Throwable) {
        ToastUtils.error(e.toString())
        baseView.hideLoading()
     /*   if (e is BaseException) {

            when {
                e.resultCode == ResultCode.TOKEN_ERROR -> {
                  //  Bus.send(FailureLoginEvent(e.resultMsg))
                }

                ResultCode.SYSTEM_MAINTENANCE == e.resultCode -> {
                }
                else -> baseView.onError(e.resultMsg)
            }
        } else {
            if (e is HttpException) {
                onException(ExceptionReason.BAD_NETWORK)
              //  LogUtils.d(e.message!!)
            } else if (e is ConnectException || e is UnknownHostException) {
                onException(ExceptionReason.CONNECT_ERROR)
             //   LogUtils.d(e.message!!)
            } else if (e is InterruptedIOException) {
                onException(ExceptionReason.CONNECT_TIMEOUT)
             //   LogUtils.d(e.message!!)
            } else if (e is JsonParseException || e is JSONException || e is ParseException) {
                onException(ExceptionReason.PARSE_ERROR)
             //   LogUtils.d(e.message!!)
            }*//* else {
                onException(ExceptionReason.UNKNOWN_ERROR)
                LogUtils.d(e.message!!)
            }*//*
        }*/
    }

    override fun onComplete() {
        baseView.hideLoading()
    }

    override fun onNext(t: T) {

    }

/*    private fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.PARSE_ERROR -> {
                baseView.onError(CommonUtils.getString(R.string.parse_error))
            }

            ExceptionReason.BAD_NETWORK -> {
                baseView.onError(CommonUtils.getString(R.string.bad_network))
            }

            ExceptionReason.CONNECT_ERROR -> {
                baseView.onError(CommonUtils.getString(R.string.connect_error))
            }

            ExceptionReason.CONNECT_TIMEOUT -> {
                baseView.onError(CommonUtils.getString(R.string.connect_timeout))
            }

//            ExceptionReason.UNKNOWN_ERROR -> {
//                baseView.onError(CommonUtils.getString(R.string.unknown_error))
//            }
        }
    }*/

    enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
//        UNKNOWN_ERROR,
    }

}