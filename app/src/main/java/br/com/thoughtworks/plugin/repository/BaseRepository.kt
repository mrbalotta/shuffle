package br.com.thoughtworks.plugin.repository

import br.com.thoughtworks.base.business.exception.HttpException
import br.com.thoughtworks.base.business.exception.InternetConnectionException
import br.com.thoughtworks.plugin.api.RetrofitAPI
import retrofit2.Call
import java.io.IOException

abstract class BaseRepository {

    protected fun <R> getBodyOrThrow(call: Call<R>): R {
        try {
            val response = call.execute()
            if(response.isSuccessful) return response.body()!!
            throw HttpException(response.code(), response.message())
        } catch(e: IOException) {
            throw InternetConnectionException()
        }
    }

    abstract fun getService(): RetrofitAPI
}