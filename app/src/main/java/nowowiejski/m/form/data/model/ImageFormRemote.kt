package nowowiejski.m.form.data.model

import android.net.Uri
import nowowiejski.m.form.domain.model.ImageForm

data class ImageFormRemote(
        val name: String,
        val fileUri: Uri?,
        override val type: FormType = FormType.IMAGE
) : BaseForm() {

    fun toImageForm() = ImageForm(name, null)

    constructor(form: ImageForm) : this(
            form.name,
            form.uri
    )

}