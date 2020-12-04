package com.payclip.examplecleancode.permissions

import android.app.Activity

interface PermissionChecker {

    enum class Permission {
        ACCOUNT
    }

    fun check(vararg permissions: Permission): Boolean

    suspend fun requestPermissions(activity: Activity, vararg permissions: Permission) : Boolean
}
