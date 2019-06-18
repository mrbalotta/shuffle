package br.com.thoughtworks.plugin.strategy.connectivity

import android.content.Context
import android.net.ConnectivityManager
import br.com.thoughtworks.base.business.interactor.ConnectivityStrategy

class ConnectivityStrategyImpl(context: Context): ConnectivityStrategy {
    override val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        val networkInfo = manager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}
