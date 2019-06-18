package br.com.thoughtworks.base.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class ViewHolderFactoryMediator<P> {
    open fun getViewType(position: Int, list: List<P?>) = 0

    abstract fun createViewHolderFactory(code: Int): ViewHolderFactory<P>

    fun getViewHolderFactory(code: Int): ViewHolderFactory<P> {
        return createViewHolderFactory(code)
    }
}

abstract class ViewHolderFactory<P> {
    abstract fun create(parent: ViewGroup): ViewHolder<P>

    protected fun inflate(@LayoutRes layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }
}
