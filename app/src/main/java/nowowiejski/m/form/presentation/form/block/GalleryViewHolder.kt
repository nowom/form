package nowowiejski.m.form.presentation.form.block

import android.view.LayoutInflater
import android.view.ViewGroup
import nowowiejski.m.form.databinding.ItemImageBinding
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.model.ImageForm
import nowowiejski.m.form.presentation.form.ItemListener
import nowowiejski.m.myweather.core.layout.BlockViewHolder
import nowowiejski.m.myweather.core.layout.BlockViewHolderFactory
import nowowiejski.m.myweather.core.layout.SimpleCastBlockBindMapper


class GalleryViewHolderFactory(
    private val itemListener: ItemListener
    ) : BlockViewHolderFactory<Form> {

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): BlockViewHolder<*, Form> {
        val view = ItemImageBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(view, itemListener)
    }
}


class GalleryViewHolder(
    private val binding: ItemImageBinding,
    private val itemListener: ItemListener
    ) : BlockViewHolder<ImageForm, Form>(
    binding.root,
    SimpleCastBlockBindMapper()
) {

    override fun onBind(mapped: ImageForm) {
        binding.item = mapped
        binding.eventListener = itemListener
        binding.executePendingBindings()
    }
}
