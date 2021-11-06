package com.anz.core.extensions

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.startActivity(noinline actionOnIntent: (Intent.()->Unit)? = null) {
    val intent = Intent(this, T::class.java)
    actionOnIntent?.invoke(intent)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startActivityInClearNewTask() {
    val intent = Intent(this, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}
