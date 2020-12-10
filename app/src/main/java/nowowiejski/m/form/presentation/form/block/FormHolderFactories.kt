package nowowiejski.m.form.presentation.form.block

import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.presentation.form.FormViewModel
import nowowiejski.m.form.presentation.form.ItemListener
import nowowiejski.m.myweather.core.layout.BlockViewHolderFactory
import nowowiejski.m.myweather.core.layout.ViewHoldersBuilder

class FormHolderFactories(
        private val itemListener: ItemListener,
        private val viewModel: FormViewModel
) : ViewHoldersBuilder<Form> {
    override fun buildViewHolderFactories(): Map<Int, BlockViewHolderFactory<Form>> = mapOf(
            FormBlockBuilder.TEXT to TextViewHolderFactory(itemListener),
            FormBlockBuilder.PHOTO to GalleryViewHolderFactory(itemListener),
            FormBlockBuilder.PHONE to PhoneViewHolderFactory(itemListener),
            FormBlockBuilder.POSTAL_CODE to PostalCodeViewHolderFactory(itemListener)
    )
}

