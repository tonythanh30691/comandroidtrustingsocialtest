package com.android.trustingsocial.test.viewmodal

import android.app.Application
import androidx.databinding.*
import androidx.lifecycle.viewModelScope
import com.android.trustingsocial.test.LoanApplication
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.di.component.DaggerGsonComponent
import com.android.trustingsocial.test.modal.*
import com.android.trustingsocial.test.repository.LoanRepository
import com.android.trustingsocial.test.ui.screenstate.ScreenState
import com.android.trustingsocial.test.util.JsonHelper
import com.android.trustingsocial.test.util.LoanConstant
import com.codding.test.startoverflowuser.network.CustomResult
import com.codding.test.startoverflowuser.screenstate.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModal @Inject constructor(app: Application, var jsonHelper: JsonHelper) : BaseViewModal<MainState>(app) {

    private var repository : LoanRepository

    // This variable hold full income data from Json local
    private var incomes : List<Income> = emptyList()
    // Binding data
    var bankInformation : ObservableField<BankResponse>  = ObservableField()
        set(value)  {
            field = value
            field.notifyChange()
        }
    var provinces : ObservableField<ProviceResponse>  = ObservableField()
        set(value) {
            field = value
            field.notifyChange()
        }
    var incomeBinding : ObservableField<List<String>>  = ObservableField()
        set(value) {
            field = value
            field.notifyChange()
        }

    // Loan input binding data
    var phoneNumber              :  ObservableField<String>  = ObservableField()
    var name                     :  ObservableField<String>  = ObservableField()
    var nationalId               :  ObservableField<String>  = ObservableField()
    var provinceSelectedPosition :  ObservableField<Int>     = ObservableField()
    var incomeSelectedPosition   :  ObservableField<Int>     = ObservableField()

    init {
        DaggerGsonComponent.builder().build().inject(this)
        var apiService = (app as LoanApplication).getAppComponent().getLoanApiService()
        repository = LoanRepository(apiService)
    }

    fun loadRequireInformation() {
        postScreenState(ScreenState.Loading)
        launchIOCoroutines {
            var bankResult       = repository.getBankInformation()
            var loadProvincesJob = repository.getProvinves()
            incomes = loadIncomes()

            // Handler error API call
            if (bankResult is CustomResult.Error || loadProvincesJob is CustomResult.Error) {
                postScreenState(ScreenState.Render(MainState.LoadError))
            } else {
                var bankResponse = (bankResult as CustomResult.Success).data
                var provinceResponse = (loadProvincesJob as CustomResult.Success).data

                // Load data into binding variables
                bankInformation.set(bankResponse)
                provinces.set(provinceResponse)

                var incomeList = incomes.map { it.description }
                incomeBinding.set(incomeList)

                postScreenState(ScreenState.Render(MainState.LoadDone))
            }
        }

    }

    fun submitLoan() {
        postScreenState(ScreenState.Loading)
        incomeSelectedPosition.get()?.let {
            if (incomes[it].id == LoanConstant.REFJECT_INCOME_ID) {
                postScreenState(ScreenState.Render(MainState.LoadDone))
                postErrorMsg(getApplication<Application>().getString(R.string.loan_edit_text_default_income_error))
            } else {
                // Start request loan
                launchIOCoroutines {
                    var loanInput = LoanInput(
                                    name          = name.get() ?: "",
                                    phone         = phoneNumber.get() ?: "",
                                    nationalId    = nationalId.get() ?: "",
                                    monthlyIncome = incomes[it].id,
                                    province      = provinces.get()!!.provinces[provinceSelectedPosition.get()!!]
                                    )
                    var result = repository.regisLoan(loanInput)
                    when (result) {
                        is CustomResult.Success -> submitLoanSuccess(result.data)
                        else                    -> postScreenState(ScreenState.Render(MainState.LoadError))
                    }
                }
            }
        }
    }

    private fun launchIOCoroutines(call : suspend () -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            call()
        }
    }

    private fun loadIncomes() : List<Income> {
        return jsonHelper.convertClassFromJsonRaw<IncomeResponse>(getApplication(), R.raw.income).incomes
    }

    private fun submitLoanSuccess(loanResponse: LoanResponse) {
        Timber.d("submitLoanSuccess : $loanResponse")
        postScreenState(ScreenState.Render(MainState.SubmitLoanDone))
    }


}