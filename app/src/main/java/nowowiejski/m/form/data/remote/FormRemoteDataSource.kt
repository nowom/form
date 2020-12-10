package nowowiejski.m.form.data.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import nowowiejski.m.form.data.model.BaseForm

interface FormRemoteDataSource {
    fun getForms(): Observable<List<BaseForm>>
    fun saveForm(forms: List<BaseForm>): Completable
}