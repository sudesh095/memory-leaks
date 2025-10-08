package com.codexorbit.oom_memoryleaks.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView

@SuppressLint("StaticFieldLeak")
object ContextLeak {
    var context: Context? = null
    var textView: TextView? = null
}