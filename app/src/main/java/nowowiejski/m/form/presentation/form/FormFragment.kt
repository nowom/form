package nowowiejski.m.form.presentation.form

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import nowowiejski.m.form.core.utils.ReadExternalStoragePermissionManager
import nowowiejski.m.form.databinding.FragmentFormBinding
import nowowiejski.m.form.domain.model.*
import nowowiejski.m.myweather.core.layout.BlockAdapter
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class FormFragment : Fragment(), ItemListener {

    private val formViewModel: FormViewModel by lifecycleScope.viewModel(this)
    private val formAdapter: BlockAdapter<Form> by lifecycleScope.inject()
    private val divider: DividerItemDecoration by lifecycleScope.inject()

    private lateinit var readExternalStoragePermissionManager: ReadExternalStoragePermissionManager
    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = formViewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readExternalStoragePermissionManager =
                ReadExternalStoragePermissionManager(this, null).apply {
                    setPerformActionListener {
                        selectPhotoFromGallery()
                    }
                }
        binding.forms.apply {
            addItemDecoration(divider)
            adapter = formAdapter
        }
        binding.saveButton.setOnClickListener {
            formViewModel.onSaveButtonClick()
        }
        observeState()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        formViewModel.forms.observe(viewLifecycleOwner) {
            displayForm(it)
        }

        formViewModel.showToastAction.observe(viewLifecycleOwner) {
            if (it)
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        formViewModel.itemSelectedAction.observe(viewLifecycleOwner) {
            readExternalStoragePermissionManager.tryToGetPermissionForActionAndPerform()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_FROM_GALLERY -> data?.data?.let { uri ->
                    formViewModel.onImageSelected(uri)
                }
            }
        }
    }

    private fun observeState() {

    }

    private fun displayForm(forms: Collection<Form>) {
        formAdapter.setData(forms)
    }

    private fun selectPhotoFromGallery() {
        val selectFromGalleryIntent = Intent()
        selectFromGalleryIntent.type = "image/*"
        selectFromGalleryIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
                Intent.createChooser(selectFromGalleryIntent, "title"),
                REQUEST_SELECT_FROM_GALLERY
        )
    }

    override fun onImageClicked(item: ImageForm) {
        formViewModel.onImageClicked(item)
    }

    override fun onPostalCodeFilled(postalCodeForm: PostalCodeForm, postalCode: String) {
        formViewModel.onPostalCodeFilled(postalCodeForm, postalCode)
    }

    override fun onTextFilled(textForm: TextForm, name: String) {

    }

    override fun onPhoneNumberFilled(phoneNumberForm: PhoneNumberForm, phoneNumber: String) {
        formViewModel.onPhoneNumberFilled(phoneNumberForm, phoneNumber)
    }

    companion object {
        const val REQUEST_SELECT_FROM_GALLERY = 1
    }

}