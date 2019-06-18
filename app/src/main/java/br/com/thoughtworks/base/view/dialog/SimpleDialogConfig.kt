package br.com.thoughtworks.base.view.dialog

import android.content.DialogInterface

data class SimpleDialogConfig(
    val message: String,
    val okTxt: Int = android.R.string.ok,
    val noTxt: Int = 0,
    val okListener: (DialogInterface, Int) -> Unit = { d, _ -> d.dismiss()},
    val noListener: (DialogInterface, Int) -> Unit = { d, _ -> d.dismiss()})