package nowowiejski.m.form.domain

import io.reactivex.rxjava3.core.Completable
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.domain.repository.FormRepository

class SaveFormUseCase(private val formRepository: FormRepository) {


    private fun action(forms: List<Form>): Completable = formRepository.saveForm(forms)

    operator fun invoke(forms: List<Form>) =
            action(forms)
}