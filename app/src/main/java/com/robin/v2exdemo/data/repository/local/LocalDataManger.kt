package com.robin.v2exdemo.data.repository.local


class LocalDataManger {



    companion object {
        val instance: LocalDataManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LocalDataManger()
        }
    }

    constructor(){

    }


}