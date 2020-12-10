package nowowiejski.m.myweather.core.layout

interface BlocksBuilder<in S> {
    fun buildBlocks(item: S): List<Int>
}