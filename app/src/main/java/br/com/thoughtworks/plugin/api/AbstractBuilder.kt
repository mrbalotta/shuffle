package br.com.thoughtworks.plugin.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class AbstractBuilder<R>(protected val baseUrl: String,
                                  private val interceptors: List<Interceptor>) {

    protected fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().let {
            if(interceptors.isEmpty()) for(interceptor in interceptors) it.addInterceptor(interceptor)
            it.connectTimeout(5, TimeUnit.SECONDS)
            it.readTimeout(5, TimeUnit.SECONDS)
            it.writeTimeout(5, TimeUnit.SECONDS)
            it.build()
        }
    }

    abstract fun build(): R
}