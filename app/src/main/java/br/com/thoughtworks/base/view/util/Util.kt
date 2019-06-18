package br.com.thoughtworks.base.view.util

import android.view.View
import android.widget.ImageView
import br.com.thoughtworks.R
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun loadImageIntoView(itemView: View, view: ImageView, url: String) {
    Glide
        .with(itemView)
        .load(url)
        .transition(GenericTransitionOptions.with(R.anim.zoom_in))
        .apply(
            RequestOptions()
                .centerCrop())
        .into(view)
}