package nowowiejski.m.form.domain.model

import android.net.Uri

sealed class Form

data class TextForm(val name: String) : Form()
data class PostalCodeForm(val name: String, var postalCode: String? = null) : Form()
data class PhoneNumberForm(val name: String, var phoneNumber: Long? = null) : Form()
data class ImageForm(val name: String, val uri: Uri?) : Form()