package nowowiejski.m.form.core.utils

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.fragment.app.Fragment

class ReadExternalStoragePermissionManager(
    fragment: Fragment? = null,
    activity: Activity? = null
) : PermissionManager(fragment, activity,true) {


    override val permissionId: Int
        get() = READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_ID

    override fun tryToGetPermissionForActionAndPerform() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.tryToGetPermissionForActionAndPerform()
        } else {
            performAction?.invoke()
        }
    }

    override fun performActionIfPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.performActionIfPermissionGranted()
        } else {
            performAction?.invoke()
        }
    }

    override val permission: String?
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Manifest.permission.READ_EXTERNAL_STORAGE
        } else null

    override val noPermissionInfoMessage: String
        get() = "NO READ EXTERNAL STORAGE PERMISSION"

    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_ID = 3
    }
}

