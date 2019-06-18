package br.com.thoughtworks.base.view.ui

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity: AppCompatActivity() {

    fun resetToolbar(toolbar: Toolbar, homeAsUpEnabled: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
    }

    fun getToolbar(): ActionBar? {
        return supportActionBar
    }
}