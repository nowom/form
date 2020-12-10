package nowowiejski.m.form.data.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import nowowiejski.m.form.data.model.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class FormRemoteDataSourceImpl : FormRemoteDataSource {

    val form = FormRemote(listOf(
            TextFormRemote("Text ", TextType.TEXT.type),
            ImageFormRemote("Gallery", null),
            TextFormRemote("Phone number", TextType.PHONE_NUMBER.type),
            ImageFormRemote("Gallery 2", null),
    ))

    override fun getForms(): Observable<List<BaseForm>> =
            Observable.fromArray(form.fields).delay(3, TimeUnit.SECONDS)

    override fun saveForm(forms: List<BaseForm>): Completable =
            Completable.create { subscriber ->
                subscriber.onComplete()
            }.delay(Random.nextLong(3, 6), TimeUnit.SECONDS)


}