package com.yunwei.base.rx

import com.example.module_base.common.ResultCode
import com.example.module_base.data.BaseResp
import com.example.module_base.rx.BaseException
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFuncBoolean<T> : Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(t: BaseResp<T>): Observable<Boolean> {
        if (t.resultCode != ResultCode.Success) {
            return Observable.error(BaseException(t.resultCode, t.resultMsg))
        }
        return Observable.just(true)
    }
}