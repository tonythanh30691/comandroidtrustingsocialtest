package com.android.trustingsocial.test.viewmodal

import android.app.Application
import androidx.databinding.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.trustingsocial.test.BR
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.modal.*
import com.android.trustingsocial.test.repository.LoanRepository
import com.android.trustingsocial.test.ui.screenstate.ScreenState
import com.android.trustingsocial.test.util.JsonHelper
import com.android.trustingsocial.test.util.LoanConstant
import com.codding.test.startoverflowuser.network.CustomResult
import com.codding.test.startoverflowuser.screenstate.MainState
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModal(app: Application) : BaseViewModal<MainState>(app) {

    private var repository = LoanRepository(app)
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
    var phoneNumber :  ObservableField<String>  = ObservableField()
    var name :  ObservableField<String>  = ObservableField()
    var nationalId :  ObservableField<String>  = ObservableField()
    var provinceSelectedPosition :  ObservableField<Int>  = ObservableField()
    var incomeSelectedPosition :  ObservableField<Int>  = ObservableField()

    fun loadRequireInformation() {
        postScreenState(ScreenState.Loading)
        // Load bank
        launchIOCoroutines {
            var result = repository.getBankInformation()
            when (result) {
                is CustomResult.Success -> loadBankInfoSuccess(result.data)
                else                    -> postScreenState(ScreenState.Render(MainState.LoadError))
            }
        }
        // Load provinces
        launchIOCoroutines {
            var result = repository.getProvinves()
            when (result) {
                is CustomResult.Success -> loadProvincesSuccess(result.data)
                else                    -> postScreenState(ScreenState.Render(MainState.LoadError))
            }
        }
        // Load income from json
        launchIOCoroutines {
            incomes = JsonHelper.convertClassFromJsonRaw<IncomeResponse>(getApplication(), R.raw.income).incomes
            var incomeList = incomes.map { it.description }
            incomeBinding.set(incomeList)
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
                                    name = name.get() ?: "",
                                    phone = phoneNumber.get() ?: "",
                                    nationalId = nationalId.get() ?: "",
                                    monthlyIncome = incomes[it].id,
                                    province = provinces.get()!!.provinces[provinceSelectedPosition.get()!!]
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

    private fun submitLoanSuccess(loanResponse: LoanResponse) {
        Timber.d("submitLoanSuccess : $loanResponse")
        postScreenState(ScreenState.Render(MainState.SubmitLoanDone))
    }

    private fun launchIOCoroutines(call : suspend () -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            call()
        }
    }

    private fun loadBankInfoSuccess(bankResponse: BankResponse) {
        bankInformation.set(bankResponse)
        postScreenState(ScreenState.Render(MainState.LoadDone))
    }

    private fun loadProvincesSuccess(provinceResponse: ProviceResponse) {
        provinces.set(provinceResponse)
        postScreenState(ScreenState.Render(MainState.LoadDone))
    }

}