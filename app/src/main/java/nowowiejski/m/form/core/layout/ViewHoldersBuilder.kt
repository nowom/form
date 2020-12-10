package nowowiejski.m.myweather.core.layout


interface ViewHoldersBuilder<S> {
    fun buildViewHolderFactories(): Map<Int, BlockViewHolderFactory<S>>
}
