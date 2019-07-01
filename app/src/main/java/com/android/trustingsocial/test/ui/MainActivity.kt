package com.android.trustingsocial.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.databinding.ActivityMainBinding
import com.android.trustingsocial.test.ui.screenstate.ScreenState
import com.android.trustingsocial.test.util.getViewModal
import com.android.trustingsocial.test.util.showToast
import com.android.trustingsocial.test.util.testinghelper.EspressoIdlingResource
import com.android.trustingsocial.test.viewmodal.MainViewModal
import com.codding.test.startoverflowuser.screenstate.MainState
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModal: MainViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initialSetup()
        viewModal.loadRequireInformation()
        EspressoIdlingResource.increment()
    }

    private fun initialSetup() {
        Timber.d("initialSetup")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModal = getViewModal(viewModelFactory)
        binding.viewmodel = viewModal
        viewModal.modalState.observe(::getLifecycle, ::updateUI)
        viewModal.errorMsg.observe(::getLifecycle, ::showErrorMessage)

        binding.btnSubmit.setOnClickListener{validateAndSubmitLoan()}
    }


    private fun validateAndSubmitLoan() {
        if (!binding.eTxtPhone.isInputValid())      return
        if (!binding.eTxtName.isInputValid())       return
        if (!binding.eTxtNationalId.isInputValid()) return
        viewModal.submitLoan()
    }

    private fun showErrorMessage(msg : String) {
        showToast(msg)
    }


    private fun updateUI(screenState : ScreenState<MainState>?) {
        Timber.d("updateUI with state: $screenState")
        when (screenState) {
            ScreenState.Loading   -> showLoading()
            is ScreenState.Render -> processLoadDataState(screenState.renderState)
        }
    }

    private fun processLoadDataState(mainState: MainState) {
        Timber.d("processLoadDataState with state: $mainState")
        showLoading(false)
        when (mainState) {
            MainState.LoadDone -> {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
            MainState.SubmitLoanDone -> {
                showToast(getString(R.string.loan_input_submit_success))
                resetScreen()
            }
            MainState.LoadError -> {
                showToast(getString(R.string.connection_error))
            }
        }
    }

    private fun resetScreen() {
        binding.eTxtName.setText("")
        binding.eTxtNationalId.setText("")
        binding.eTxtPhone.setText("")
        binding.spnAddress.setSelection(0)
        binding.spnIncome.setSelection(0)
        binding.eTxtName.requestFocus()
    }

    private fun showLoading(isShow : Boolean = true) {
        Timber.d("showLoading $isShow")
        if (isShow) {
            binding.progressBar.visibility = View.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            binding.progressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

}
