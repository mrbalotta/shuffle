package br.com.thoughtworks.base.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.thoughtworks.R

object GlobalNavigations {
    fun navigateToOffline(navController: NavController, options: NavOptions = getDefaultOptions().build()) {
        navController
            .navigate(R.id.action_global_offline, null, options)
    }

    fun getDefaultOptions(): NavOptions.Builder {
        return NavOptions.Builder()
            .setPopExitAnim(R.anim.fade_out)
            .setExitAnim(R.anim.fade_out)
            .setEnterAnim(R.anim.fade_in)
    }
}