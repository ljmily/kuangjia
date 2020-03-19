package com.example.module_base.rx

import com.example.module_base.common.ResultCode
import com.example.module_base.data.BaseResp
import com.example.module_base.event.InterceptEvent
import io.reactivex.Observable
import io.reactivex.functions.Function
import org.greenrobot.eventbus.EventBus

class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.resultCode != ResultCode.Success) {
            if (t.resultCode == "1000"){
                EventBus.getDefault().post(InterceptEvent(t.resultMsg))
            }
            return Observable.error(BaseException(t.resultCode, t.resultMsg))
        }
        return Observable.just(t.data)
    }
}