package nowowiejski.m.form.domain

import io.reactivex.rxjava3.core.Observable
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.repository.FormRepository

class GetFormUseCase(private val formRepository: FormRepository) {

    private fun action(): Observable<List<Form>> =
        formRepository.getForms()

    operator fun invoke() =
        action()

}