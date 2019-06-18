package br.com.thoughtworks.base.business.interactor

import android.net.ConnectivityManager

interface ConnectivityStrategy {
    fun isConnected(): Boolean
    val manager: ConnectivityManager
}