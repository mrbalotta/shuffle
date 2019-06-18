package br.com.thoughtworks.base.gateway.di

import br.com.thoughtworks.base.business.interactor.ConnectivityStrategy

interface ConnectivityInjector {
    companion object {
        lateinit var injector: ConnectivityInjector
    }

    val connectivityStrategy: ConnectivityStrategy
}