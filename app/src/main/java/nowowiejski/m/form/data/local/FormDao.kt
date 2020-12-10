package nowowiejski.m.form.data.local

import io.reactivex.rxjava3.core.Completable

interface FormDao {
    fun saveForms(): Completable
}