package nowowiejski.m.form.presentation.form.block

import nowowiejski.m.form.core.layout.BlockTypeMapper
import nowowiejski.m.form.domain.model.*

class FormBlockBuilder: BlockTypeMapper<Form> {

    override fun mapBlock(item: Form): Int =
        when(item){
            is TextForm -> TEXT
            is PostalCodeForm -> POSTAL_CODE
            is PhoneNumberForm -> PHONE
            is ImageForm -> PHOTO
        }

    companion object {
        const val TEXT = 1
        const val POSTAL_CODE = 2
        const val PHONE = 3
        const val PHOTO = 4
    }


}