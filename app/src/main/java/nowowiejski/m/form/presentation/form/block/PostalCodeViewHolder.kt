package nowowiejski.m.form.presentation.form.block

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import nowowiejski.m.form.databinding.ItemPhoneNumberBinding
import nowowiejski.m.form.databinding.ItemPostalCodeBinding
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.model.PhoneNumberForm
import nowowiejski.m.form.domain.model.PostalCodeForm
import nowowiejski.m.form.presentation.form.ItemListener
import nowowiejski.m.myweather.core.layout.BlockViewHolder
import nowowiejski.m.myweather.core.layout.BlockViewHolderFactory
import nowowiejski.m.myweather.core.layout.SimpleCastBlockBindMapper

class PostalCodeViewHolderFactory(
        private val itemListener: ItemListener
) : BlockViewHolderFactory<Form> {

    override fun createViewHolder(
            parent: ViewGroup,
            inflater: LayoutInflater
    ): BlockViewHolder<*, Form> {
        val view = ItemPostalCodeBinding.inflate(inflater, parent, false)
        return PostalCodeViewHolder(view, itemListener)
    }
}


class PostalCodeViewHolder(
        private val binding: ItemPostalCodeBinding,
        private val itemListener: ItemListener
) : BlockViewHolder<PostalCodeForm, Form>(
        binding.root,
        SimpleCastBlockBindMapper()
) {

    override fun onBind(mapped: PostalCodeForm) {
        binding.item = mapped
        binding.executePendingBindings()
        binding.input.doAfterTextChanged {
            itemListener.onPostalCodeFilled(mapped, it.toString())
        }
    }
}
