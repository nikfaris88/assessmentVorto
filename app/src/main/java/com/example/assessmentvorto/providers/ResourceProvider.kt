package com.example.assessmentvorto.providers

import android.content.Context
import javax.inject.Inject

class ResourceProvider() {

    lateinit var context: Context

    @Inject
    constructor(context: Context) : this() {
        this.context = context
    }

}