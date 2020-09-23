package com.robin.v2exdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.robin.v2exdemo.app.network.apiService
import com.robin.v2exdemo.data.model.bean.HotBean
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class LatestViewModel : BaseViewModel() {
    var latestData: MutableLiveData<ResultState<ArrayList<HotBean>>> = MutableLiveData()
    fun getLatest() {
        requestNoCheck(
            { apiService.getLatestList() },
            latestData,
            true

        )
    }
}