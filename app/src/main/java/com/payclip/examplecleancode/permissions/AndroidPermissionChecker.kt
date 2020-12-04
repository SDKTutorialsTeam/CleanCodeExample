package com.payclip.examplecleancode.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidPermissionChecker(private val context: Context) : PermissionChecker {

    override fun check(vararg permissions: PermissionChecker.Permission): Boolean =
        permissions.map { permission ->
            ContextCompat.checkSelfPermission(
                context,
                permission.toAndroidId()
            ) == PackageManager.PERMISSION_GRANTED
        }.all { it }

    override suspend fun requestPermissions(activity: Activity, vararg permissions: PermissionChecker.Permission): Boolean =
        suspendCancellableCoroutine { continuation ->
            Dexter.withActivity(activity)
                .withPermissions(permissions.map { it.toAndroidId() })
                .withListener(object : BaseMultiplePermissionsListener() {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        super.onPermissionsChecked(report)
                        continuation.resume(report?.areAllPermissionsGranted() ?: false)
                    }
                }).withErrorListener {
                    continuation.resume(false)
                }.check()
        }

}
