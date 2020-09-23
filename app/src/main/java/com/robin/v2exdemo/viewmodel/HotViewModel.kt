package com.robin.v2exdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.robin.v2exdemo.app.network.apiService
import com.robin.v2exdemo.data.model.bean.HotBean
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class HotViewModel :BaseViewModel() {

    var hotData: MutableLiveData<ResultState<ArrayList<HotBean>>> = MutableLiveData()

    fun getHotList(){
        requestNoCheck(
            { apiService.getHotList()},
            hotData,
            true
        )
    }
}