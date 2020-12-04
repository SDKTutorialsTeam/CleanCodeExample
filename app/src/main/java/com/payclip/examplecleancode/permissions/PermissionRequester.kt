package com.payclip.examplecleancode.permissions

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener

class PermissionRequester(private val activity: Activity) {

    fun request(vararg permissions: PermissionChecker.Permission, continuation: (Boolean) -> Unit) {
        Dexter
            .withActivity(activity)
            .withPermissions(permissions.map { it.toAndroidId() })
            .withListener(object : BaseMultiplePermissionsListener() {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    continuation(report?.areAllPermissionsGranted() ?: false)
                }
            }).check()
    }

}
