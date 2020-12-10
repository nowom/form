package nowowiejski.m.form.core.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class PermissionManager(
    private var fragment: Fragment? = null,
    private var activity: Activity? = null,
    private var required: Boolean
) {

    protected abstract val permissionId: Int
    protected abstract val permission: String?
    protected abstract val noPermissionInfoMessage: String?

    private val context: Context? by lazy {
        if (activity != null)
            activity
        else fragment?.context
    }

    var performAction: (() -> Unit)? = null

    fun setPerformActionListener(performAction: (() -> Unit)?) {
        this.performAction = performAction
    }

    open fun performActionIfPermissionGranted() {
        if (context?.let { ContextCompat.checkSelfPermission(it, permission.orEmpty()) }
            == PackageManager.PERMISSION_GRANTED
        ) {
            performAction?.invoke()
        }
    }

    open fun tryToGetPermissionForActionAndPerform() {
        if (fragment != null) {
            tryToGetPermissionForActionAndPerformInFragment()
        } else if (activity != null) {
            tryToGetPermissionForActionAndPerformInActivity()
        }
    }

    private fun tryToGetPermissionForActionAndPerformInFragment() {
        fragment?.let { fragment ->
            if (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        permission.orEmpty()
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                if (fragment.shouldShowRequestPermissionRationale(permission.orEmpty())) {
                    if (required) {
                        Toast.makeText(context, noPermissionInfoMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        performAction?.invoke()
                    }
                } else {
                    fragment.requestPermissions(
                        arrayOf(permission),
                        permissionId
                    )
                }
            } else {
                performAction?.invoke()
            }
        }
    }

    private fun tryToGetPermissionForActionAndPerformInActivity() {
        activity?.let { activity ->
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission.orEmpty()
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permission.orEmpty()
                    )
                ) {
                    if (required) {
                        Toast.makeText(context, noPermissionInfoMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        performAction?.invoke()
                    }
                } else {
                    ActivityCompat.requestPermissions(
                        activity, arrayOf(permission),
                        permissionId
                    )
                }
            } else {
                performAction?.invoke()
            }
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) || !required
            ) {
                performAction?.invoke()
            } else {
                Toast.makeText(context, noPermissionInfoMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
