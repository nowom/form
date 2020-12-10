package nowowiejski.m.myweather.core.layout

import android.view.LayoutInflater
import android.view.ViewGroup

interface BlockViewHolderFactory<S> {
    fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): BlockViewHolder<*, S>
}


interface BlockBindMapper<out T, in S> {

    fun mapBindProperty(property: S): T
}

@SuppressWarnings()
class SimpleCastBlockBindMapper<T, S> : BlockBindMapper<T, S> where T : S {
    override fun mapBindProperty(property: S): T = property as T
}