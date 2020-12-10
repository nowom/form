package nowowiejski.m.form.di

import androidx.recyclerview.widget.DividerItemDecoration
import nowowiejski.m.form.core.utils.ApplicationSchedulerProvider
import nowowiejski.m.form.core.utils.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory { DividerItemDecoration(androidContext(), DividerItemDecoration.VERTICAL) }
    factory<SchedulerProvider> { ApplicationSchedulerProvider() }
}