package nowowiejski.m.form.core.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ApplicationSchedulerProvider : SchedulerProvider{

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

}