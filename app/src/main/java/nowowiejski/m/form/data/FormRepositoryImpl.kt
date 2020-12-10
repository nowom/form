package nowowiejski.m.form.data

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import nowowiejski.m.form.data.local.FormDao
import nowowiejski.m.form.data.model.ImageFormRemote
import nowowiejski.m.form.data.model.TextFormRemote
import nowowiejski.m.form.data.remote.FormRemoteDataSource
import nowowiejski.m.form.domain.model.*
import nowowiejski.m.form.domain.repository.FormRepository
import java.util.concurrent.TimeUnit

class FormRepositoryImpl(private val formRemoteDataSource: FormRemoteDataSource,
                         private val formDao: FormDao) : FormRepository {

    override fun getForms(): Observable<List<Form>> {
        return formRemoteDataSource.getForms().map { fields ->
            val items = mutableListOf<Form>()
            fields.forEach { field ->
                when (field) {
                    is TextFormRemote -> items.add(field.toTextForm())
                    is ImageFormRemote -> items.add(field.toImageForm())
                }
            }
            items
        }
    }

    override fun saveForm(forms: List<Form>): Completable =
            postToServer(forms).andThen(saveToLocal(forms))

    private fun saveToLocal(forms: List<Form>): Completable =
            formDao.saveForms()

    private fun postToServer(forms: List<Form>): Completable = Completable.fromCallable {
        val remoteForms = forms.map {
            when (it) {
                is ImageForm -> ImageFormRemote(it)
                is TextForm -> TextFormRemote(it)
                is PostalCodeForm -> TextFormRemote(it)
                is PhoneNumberForm -> TextFormRemote(it)
            }
        }
        formRemoteDataSource.saveForm(remoteForms)
    }

//    private suspend fun getCharactersFromRemote(): List<Character> {
//        return api.getCharacters()
//                .results
//                .map { it.toCharacter() }
//    }
//
//    private suspend fun getCharactersFromLocal(): List<Character> {
//        return dao.getCharacters()
//                .map { it.toCharacter() }
//    }
//
//    private suspend fun saveCharactersToLocal(characters: List<Character>) {
//        characters.map { CharacterCached(it) }
//                .toTypedArray()
//
//    }
}