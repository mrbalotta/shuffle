package br.com.thoughtworks.feature.splash.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.thoughtworks.R
import br.com.thoughtworks.base.gateway.di.ConnectivityInjector
import br.com.thoughtworks.base.view.navigation.GlobalNavigations

class SplashFragment: Fragment() {
    private val connectivityStrategy by lazy { ConnectivityInjector.injector.connectivityStrategy }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({ navigate()}, 2000)
    }

    private fun navigate() {
        if(connectivityStrategy.isConnected()) navigateToPlaylist()
        else navigateToOffline()
    }

    private fun navigateToOffline() {
        GlobalNavigations.navigateToOffline(findNavController(), preventPopupToSplash())
    }

    private fun navigateToPlaylist() {
        findNavController()
            .navigate(R.id.action_splash_to_playlist, null, preventPopupToSplash())
    }

    private fun preventPopupToSplash(): NavOptions {
        return GlobalNavigations
                .getDefaultOptions()
                .setPopUpTo(R.id.splashFragment, true)
                .build()
    }
}