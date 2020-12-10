package nowowiejski.m.form.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    protected lateinit var disposable : CompositeDisposable

    init {
        subscribe()
    }

    protected fun Disposable.track() {
        disposable.add(this)
    }

    fun subscribe() {
        disposable = CompositeDisposable()
    }

    fun unSubscribe() {
        if(!disposable.isDisposed) disposable.dispose()
    }

    override fun onCleared() {
        unSubscribe()
        super.onCleared()
    }
}