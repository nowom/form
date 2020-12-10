package nowowiejski.m.myweather.core.layout

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BlockViewHolder<in T, in S>(
    view: View,
    private val blockBindMapper: BlockBindMapper<T, S>
) : RecyclerView.ViewHolder(view) {

    open fun bind(item: S) {
        onBind(blockBindMapper.mapBindProperty(item))
    }

    abstract fun onBind(mapped: T)
}
