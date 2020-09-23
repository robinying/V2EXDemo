package com.robin.v2exdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.robin.v2exdemo.app.network.apiService
import com.robin.v2exdemo.data.model.bean.HotBean
import com.robin.v2exdemo.data.model.bean.NodeInfo
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class ShowViewModel : BaseViewModel() {

    var nodeData: MutableLiveData<ResultState<NodeInfo>> = MutableLiveData()
    var androidNode: NodeInfo? = null
    var iosNode: NodeInfo? = null
    var pythonNode: NodeInfo? = null


    fun getShowInfo(node: String) {
        requestNoCheck(
            { apiService.getNodeInfo(node) },
            nodeData,
            false
        )
    }
}