package nowowiejski.m.form.core.layout.renderer

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideImageRenderer : ImageRenderer {


    override fun renderImage(
        uri: Uri,
        imageView: ImageView,
        width: Int?,
        height: Int?,
        scaleType: ScaleType?
    ) {
        val creator = Glide.with(imageView.context).load(uri)
        when {
            scaleType != null -> when (scaleType) {
                ScaleType.CENTER_CROP -> creator.centerCrop()
                ScaleType.CENTER_INSIDE -> creator.centerInside()
            }
            width != null || height != null -> creator.apply(
                RequestOptions().override(
                    width ?: 0,
                    height ?: 0
                )
            )
            else -> creator.fitCenter()
        }
        creator.into(imageView)
    }
}