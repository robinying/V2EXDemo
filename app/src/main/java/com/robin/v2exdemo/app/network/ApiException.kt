package com.robin.v2exdemo.app.network

class ApiException(val msg: String, val status: Int) :
    Throwable()