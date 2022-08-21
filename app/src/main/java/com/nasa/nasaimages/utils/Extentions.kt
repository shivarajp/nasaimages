package com.nasa.nasaimages.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}