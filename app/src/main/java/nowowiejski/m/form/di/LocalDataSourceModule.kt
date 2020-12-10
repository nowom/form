package nowowiejski.m.form.di

import nowowiejski.m.form.data.local.FormDao
import nowowiejski.m.form.data.local.FormDaoImpl
import org.koin.dsl.module

val localDataSourceModule = module {
    factory<FormDao> { FormDaoImpl() }
}