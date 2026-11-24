package me.hgj.jetpackmvvm.callback.livedata

import me.hgj.jetpackmvvm.callback.livedata.event.EventLiveData

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 仅分发 owner observe 后 才新拿到的数据
 * 可避免共享作用域 VM 下 liveData 被 observe 时旧数据倒灌的情况
 *
 * 实现委托给 [EventLiveData]（延迟清空消息），不再反射 androidx.lifecycle.LiveData 的私有字段，
 * 避免依赖字段名导致的 NoSuchFieldException 风险
 */
class UnPeekLiveData<T> : EventLiveData<T>()
