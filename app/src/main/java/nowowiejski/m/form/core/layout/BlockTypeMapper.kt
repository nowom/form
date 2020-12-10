package nowowiejski.m.form.core.layout

interface BlockTypeMapper<in S> {
    fun mapBlock(item: S): Int
}