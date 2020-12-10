package nowowiejski.m.form.presentation.form.block

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.viewbinding.ViewBinding
import kotlinx.android.synthetic.main.item_text.view.*
import nowowiejski.m.form.databinding.ItemTextBinding
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.model.TextForm
import nowowiejski.m.form.presentation.form.ItemListener
import nowowiejski.m.myweather.core.layout.BlockViewHolder
import nowowiejski.m.myweather.core.layout.BlockViewHolderFactory
import nowowiejski.m.myweather.core.layout.SimpleCastBlockBindMapper

class TextViewHolderFactory(
    private val itemListener: ItemListener
) :
    BlockViewHolderFactory<Form> {

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): BlockViewHolder<*, Form> {
        val view = ItemTextBinding.inflate(inflater, parent, false)
        return TextViewHolder(view, itemListener)
    }
}


class TextViewHolder(
    private val binding: ItemTextBinding,
    private val itemListener: ItemListener
) :
    BlockViewHolder<TextForm, Form>(
        binding.root,
        SimpleCastBlockBindMapper()
    ) {


    override fun onBind(mapped: TextForm) {
        binding.input.doAfterTextChanged {
            itemListener.onTextFilled(mapped, it.toString())
        }
    }

}