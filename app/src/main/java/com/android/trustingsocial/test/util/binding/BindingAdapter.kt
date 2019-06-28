package com.android.trustingsocial.test.util.binding

import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:background")
    fun ImageView.setBackgroundFromUrl(url : String?) {
        url?.let {
            Picasso.get().load(it).into(this)
        }

    }

}
