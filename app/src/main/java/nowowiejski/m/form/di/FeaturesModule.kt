package nowowiejski.m.form.di

import nowowiejski.m.form.core.layout.BlockTypeMapper
import nowowiejski.m.form.data.FormRepositoryImpl
import nowowiejski.m.form.data.remote.FormRemoteDataSource
import nowowiejski.m.form.data.remote.FormRemoteDataSourceImpl
import nowowiejski.m.form.domain.GetFormUseCase
import nowowiejski.m.form.domain.SaveFormUseCase
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.repository.FormRepository
import nowowiejski.m.form.presentation.form.FormFragment
import nowowiejski.m.form.presentation.form.FormViewModel
import nowowiejski.m.form.presentation.form.block.FormBlockBuilder
import nowowiejski.m.form.presentation.form.block.FormHolderFactories
import nowowiejski.m.myweather.core.layout.BlockAdapter
import nowowiejski.m.myweather.core.layout.BlocksBuilder
import nowowiejski.m.myweather.core.layout.ViewHoldersBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featuresModule = module {

    //Data Source
    factory<FormRemoteDataSource> { FormRemoteDataSourceImpl() }

    //Repository
    factory<FormRepository> { FormRepositoryImpl(get(), get()) }

    //UseCase
    factory { GetFormUseCase(get()) }
    factory { SaveFormUseCase(get()) }

    scope(named<FormFragment>()) {
        viewModel { FormViewModel(get(), get(), get()) }
        scoped<ViewHoldersBuilder<Form>> { FormHolderFactories(get(), get()) }
        scoped<BlockTypeMapper<Form>> { FormBlockBuilder() }
        scoped { BlockAdapter<Form>(get(), get()) }
    }


}