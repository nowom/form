package nowowiejski.m.myweather.core.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nowowiejski.m.form.core.layout.BlockTypeMapper

class BlockAdapter<S> constructor(
    private val blockHolderFactories: ViewHoldersBuilder<S>,
    private val blockMapper: BlockTypeMapper<S>
) : RecyclerView.Adapter<BlockViewHolder<*, S>>() {

    protected val items: MutableList<S> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder<*, S> {
        val inflater = LayoutInflater.from(parent.context)
        return blockHolderFactories.buildViewHolderFactories()[viewType]?.createViewHolder(
            parent,
            inflater
        ) ?: throw NoSuchElementException("Element $viewType does not exists")
    }

    override fun getItemViewType(position: Int): Int = blockMapper.mapBlock(items[position])

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: BlockViewHolder<*, S>, position: Int) {
        holder.bind(items[position])
    }

    fun setData(data: Collection<S>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}

