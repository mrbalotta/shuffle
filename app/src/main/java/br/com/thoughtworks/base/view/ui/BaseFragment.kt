package br.com.thoughtworks.base.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.thoughtworks.base.business.exception.HttpException
import br.com.thoughtworks.base.business.exception.InternetConnectionException
import br.com.thoughtworks.base.gateway.mvvm.BaseViewModel
import br.com.thoughtworks.base.gateway.mvvm.ViewState


abstract class BaseFragment<V: BaseViewModel>: Fragment() {
    protected lateinit var viewModel: V

    protected abstract fun getViewModelClass(): Class<V>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupViews(view)
        observeViewModel()
    }

    protected open fun setupViews(view: View) {}

    protected fun setupToolbar(toolbar: Toolbar, homeAsUpEnabled: Boolean) {
        (activity as? BaseActivity)?.resetToolbar(toolbar, homeAsUpEnabled)
    }

    protected fun getToolbar(): ActionBar? {
        return (activity as? BaseActivity)?.getToolbar()
    }

    protected open fun setupViewModel() {
        val clazz = getViewModelClass()
        viewModel = ViewModelProviders.of(this).get(clazz)
    }

    protected open fun observeViewModel() {
        observeAllChannels()
    }

    protected fun observeAllChannels() {
        viewModel.getChannels().forEach { observeChannel(it) }
    }

    protected fun observeChannel(channelName: String) {
        viewModel.observe(channelName,this, Observer { v-> handleResponse(v) })
    }

    protected open fun handleResponse(state: ViewState) {
        if(!state.handled)  {
            state.handled = true
            if (state.isError()) {
                handleThrowable(state.output.error)
            } else {
                handleSuccess(state.output.value)
            }
        }
    }

    private fun handleThrowable(error: Throwable?) {
        when(error) {
            is HttpException -> handleHttpError(error)
            is InternetConnectionException -> handleConnectionError()
            else -> handleError(error)
        }
    }

    protected open fun handleHttpError(error: HttpException) {}

    protected open fun handleConnectionError() {}

    protected open fun handleError(error: Throwable?) {}

    protected open fun handleSuccess(value: Any?) {}
}