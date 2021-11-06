package com.anz.demo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom application to initialize anything that needs to be activated from application start.
 */
@HiltAndroidApp
class App : Application()