package nowowiejski.m.form.core.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import nowowiejski.m.form.core.layout.renderer.GlideImageRenderer
import nowowiejski.m.form.core.layout.renderer.ImageRenderer
import nowowiejski.m.form.core.layout.renderer.ScaleType
import org.koin.core.KoinComponent
import org.koin.java.KoinJavaComponent.inject

@BindingAdapter(value = ["app:imageUrl", "app:scaleType", "app:placeholder"], requireAll = false)
fun imageFromUri(imageView: ImageView, imageUrl: Uri?, scaleType: ScaleType?, @DrawableRes placeholder: Int) {
    val creator = Glide.with(imageView.context).load(imageUrl).placeholder(placeholder)
    when {
        scaleType != null -> when (scaleType) {
            ScaleType.CENTER_CROP -> creator.centerCrop()
            ScaleType.CENTER_INSIDE -> creator.centerInside()
        }
        else -> creator.fitCenter()
    }
    creator.into(imageView)
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
