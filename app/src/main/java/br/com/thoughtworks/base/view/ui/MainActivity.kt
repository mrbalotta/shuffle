package br.com.thoughtworks.base.view.ui

import android.os.Bundle
import br.com.thoughtworks.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        finish()
    }
}
