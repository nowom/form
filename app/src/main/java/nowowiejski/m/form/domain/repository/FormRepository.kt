package nowowiejski.m.form.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import nowowiejski.m.form.domain.model.Form

interface FormRepository {
    fun getForms(): Observable<List<Form>>
    fun saveForm(forms: List<Form>): Completable
}