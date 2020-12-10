package nowowiejski.m.form.data.model

import nowowiejski.m.form.domain.model.*

data class TextFormRemote(
        private val name: String,
        private val textType: String,
        override val type: FormType = FormType.TEXT
) : BaseForm() {

    fun toTextForm() = when (textType) {
        TextType.PHONE_NUMBER.type -> PhoneNumberForm(name)
        TextType.POST_ADDRESS.type -> PostalCodeForm(name)
        else -> TextForm(name)
    }

    constructor(form: TextForm) : this(
            form.name,
            TextType.TEXT.type
    )

    constructor(form: PhoneNumberForm) : this(
            form.name,
            TextType.PHONE_NUMBER.type
    )

    constructor(form: PostalCodeForm) : this(
            form.name,
            TextType.PHONE_NUMBER.type
    )
}