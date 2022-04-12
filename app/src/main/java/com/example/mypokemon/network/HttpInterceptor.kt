package com.example.mypokemon.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }


}