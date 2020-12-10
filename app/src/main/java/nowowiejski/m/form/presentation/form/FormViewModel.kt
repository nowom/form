package nowowiejski.m.form.presentation.form

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nowowiejski.m.form.core.utils.SchedulerProvider
import nowowiejski.m.form.domain.GetFormUseCase
import nowowiejski.m.form.domain.SaveFormUseCase
import nowowiejski.m.form.domain.model.Form
import nowowiejski.m.form.core.base.BaseViewModel
import nowowiejski.m.form.core.extensions.postNext
import nowowiejski.m.form.core.extensions.setNext
import nowowiejski.m.form.domain.model.ImageForm
import nowowiejski.m.form.domain.model.PhoneNumberForm
import nowowiejski.m.form.domain.model.PostalCodeForm
import nowowiejski.m.form.presentation.resault.Event

class FormViewModel(
        private val getFormUseCase: GetFormUseCase,
        private val saveFormUseCase: SaveFormUseCase,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _forms by lazy {
        MutableLiveData<List<Form>>().also {
            getForms(it)
        }
    }
    val forms: LiveData<List<Form>> by lazy {
        _forms
    }

    private val _itemSelected = MutableLiveData<ImageForm>()
    val itemSelected: LiveData<ImageForm> by lazy { _itemSelected }
    private val _itemSelectedAction = MutableLiveData<Event<ImageForm>>()
    val itemSelectedAction: LiveData<Event<ImageForm>> by lazy { _itemSelectedAction }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _showToastAction = MutableLiveData<Boolean>()
    val showToastAction: LiveData<Boolean> = _showToastAction

    fun onSaveButtonClick() {
        _isLoading.value = true
        forms.value?.let {
            saveFormUseCase(it)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe({
                        _showToastAction.value = true
                        _isLoading.value = false
                    }, {
                        _isLoading.value = false
                    }).track()
        }
    }

    fun onImageSelected(uri: Uri) {
        val index = forms.value?.indexOf(itemSelected.value)
        _itemSelected.setNext {
            it.copy(uri = uri)
        }
        if (index != null && index != -1) {
            itemSelected.value?.let {
                val list = forms.value?.toMutableList()?.apply {
                    set(index, it)
                }
                _forms.value = list
            }
        }
    }

    private fun getForms(formsLiveData: MutableLiveData<List<Form>>) {
        _isLoading.value = true
        getFormUseCase().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    _isLoading.value = false
                    formsLiveData.value = it
                }, {
                    _isLoading.value = false
                    Log.e(FormViewModel::class.java.simpleName, "getForms", it)
                }).track()
    }

    fun onImageClicked(item: ImageForm) {
        _itemSelectedAction.value = Event(item)
        _itemSelected.value = item
    }

    fun onTextFilled(name: String) {

    }

    fun onPhoneNumberFilled(phoneNumberForm: PhoneNumberForm, phoneNumber: String){
        phoneNumberForm.phoneNumber = phoneNumber.toLong()
    }

    fun onPostalCodeFilled(postalCodeForm: PostalCodeForm, postalCode: String){
        postalCodeForm.postalCode = postalCode
    }

}