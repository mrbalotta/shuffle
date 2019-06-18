package br.com.thoughtworks.plugin.view.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import br.com.thoughtworks.R
import br.com.thoughtworks.base.view.dialog.SimpleDialogConfig
import br.com.thoughtworks.base.view.dialog.SimpleDialogStrategy

class DialogStrategyImpl: SimpleDialogStrategy {
    override fun show(context: Context, config: SimpleDialogConfig) {
        AlertDialog.Builder(context, R.style.AlertDialogTheme).apply {
            setMessage(config.message)
            setPositiveButton(config.okTxt, config.okListener)
            if(config.noTxt != 0) setNegativeButton(config.noTxt, config.noListener)
            create()
            show()
        }
    }
}