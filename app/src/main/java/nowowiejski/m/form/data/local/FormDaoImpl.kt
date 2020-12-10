package nowowiejski.m.form.data.local

import io.reactivex.rxjava3.core.Completable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class FormDaoImpl : FormDao {

    override fun saveForms(): Completable {
        return Completable.complete().delay(Random.nextLong(2, 5), TimeUnit.SECONDS)
    }
}