package br.com.thoughtworks.base.view.dialog

import android.content.Context

interface SimpleDialogStrategy {
    fun show(context: Context, config: SimpleDialogConfig)
}