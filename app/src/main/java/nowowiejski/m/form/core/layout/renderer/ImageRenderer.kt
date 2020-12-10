package nowowiejski.m.form.core.layout.renderer

import android.net.Uri
import android.widget.ImageView

interface ImageRenderer {
    fun renderImage(
        uri: Uri,
        imageView: ImageView,
        width: Int? = null,
        height: Int? = null,
        scaleType: ScaleType? = null
    )
}

enum class ScaleType {
    CENTER_CROP, CENTER_INSIDE
}