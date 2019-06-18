package br.com.thoughtworks.feature.offline.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.thoughtworks.R
import br.com.thoughtworks.base.gateway.di.ConnectivityInjector

class OfflineFragment: Fragment() {
    private lateinit var listener: ConnectivityManagerReceiverListener


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = ConnectivityManagerReceiverListener(::connectionAvailable)
        context?.registerReceiver(listener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offline, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        context?.unregisterReceiver(listener)
    }

    private fun connectionAvailable() {
        findNavController()
            .navigate(R.id.action_offline_to_playlist, null, preventPopup())
    }

    private fun preventPopup(): NavOptions {
        return NavOptions.Builder()
            .setPopExitAnim(R.anim.fade_out)
            .setExitAnim(R.anim.fade_out)
            .setEnterAnim(R.anim.fade_in)
            .setPopUpTo(R.id.splashFragment, true).build()
    }
}

class ConnectivityManagerReceiverListener(val listener: ()->Unit): BroadcastReceiver() {
    private val connectivityStrategy by lazy { ConnectivityInjector.injector.connectivityStrategy }

    override fun onReceive(context: Context, intent: Intent) {
        if (connectivityStrategy.isConnected()) listener()
    }
}