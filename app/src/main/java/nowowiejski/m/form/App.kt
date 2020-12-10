package nowowiejski.m.form

import android.app.Application
import nowowiejski.m.form.di.koinInjector
import org.koin.android.ext.koin.androidContext

class App: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
            modules(koinInjector)
        }
    }
}