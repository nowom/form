package nowowiejski.m.form.presentation.form

import nowowiejski.m.form.domain.model.ImageForm
import nowowiejski.m.form.domain.model.PhoneNumberForm
import nowowiejski.m.form.domain.model.PostalCodeForm
import nowowiejski.m.form.domain.model.TextForm

interface ItemListener {
    fun onTextFilled(textForm: TextForm, name: String)
    fun onImageClicked(item: ImageForm)
    fun onPhoneNumberFilled(phoneNumberForm: PhoneNumberForm, phoneNumber: String)
    fun onPostalCodeFilled(phoneNumberForm: PostalCodeForm, postalCode: String)

}