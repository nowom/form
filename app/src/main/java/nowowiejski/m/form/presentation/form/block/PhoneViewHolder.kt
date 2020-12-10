package nowowiejski.m.form.presentation.form.block

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import nowowiejski.m.form.core.extensions.executeAfter
import nowowiejski.m.form.databinding.ItemPhoneNumberBinding
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.model.PhoneNumberForm
import nowowiejski.m.form.presentation.form.ItemListener
import nowowiejski.m.myweather.core.layout.BlockViewHolder
import nowowiejski.m.myweather.core.layout.BlockViewHolderFactory
import nowowiejski.m.myweather.core.layout.SimpleCastBlockBindMapper

class PhoneViewHolderFactory(
        private val itemListener: ItemListener
) : BlockViewHolderFactory<Form> {

    override fun createViewHolder(
            parent: ViewGroup,
            inflater: LayoutInflater
    ): BlockViewHolder<*, Form> {
        val view = ItemPhoneNumberBinding.inflate(inflater, parent, false)
        return PhoneViewHolder(view, itemListener)
    }
}


class PhoneViewHolder(
        private val binding: ItemPhoneNumberBinding,
        private val itemListener: ItemListener
) : BlockViewHolder<PhoneNumberForm, Form>(
        binding.root,
        SimpleCastBlockBindMapper()
) {

    override fun onBind(mapped: PhoneNumberForm) {
        binding.item = mapped
        //binding.eventListener = itemListener
        binding.executePendingBindings()
        binding.input.doAfterTextChanged {
          itemListener.onPhoneNumberFilled(mapped, it.toString())
        }
    }
}
