package com.payclip.examplecleancode.permissions

import android.Manifest

fun PermissionChecker.Permission.toAndroidId() = when (this) {
    PermissionChecker.Permission.ACCOUNT -> Manifest.permission.GET_ACCOUNTS
}