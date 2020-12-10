package nowowiejski.m.form.data.model

abstract class BaseForm {
    abstract val type: FormType
}

enum class FormType(type: String) {
    TEXT("text"), IMAGE("image")
}

enum class TextType(val type: String) {
    TEXT("Text"), POST_ADDRESS("PostalAddress"), PHONE_NUMBER("PhoneNumber")
}