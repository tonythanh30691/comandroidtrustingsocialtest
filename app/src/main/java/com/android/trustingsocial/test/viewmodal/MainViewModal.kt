package com.android.trustingsocial.test.viewmodal

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.trustingsocial.test.modal.BankResponse
import com.android.trustingsocial.test.repository.LoanRepository
import com.android.trustingsocial.test.ui.screenstate.ScreenState
import com.codding.test.startoverflowuser.network.CustomResult
import com.codding.test.startoverflowuser.screenstate.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModal(app: Application) : BaseViewModal<MainState>(app) {

    private var repository = LoanRepository(app)
    var bankInfomation : ObservableField<BankResponse>  = ObservableField()
        set(value)  {
            field = value
            field.notifyChange()
        }

    fun loadBankInfomation() {
        postScreenState(ScreenState.Loading)
        viewModelScope.launch (Dispatchers.IO) {
            // Delay to simulate network slow request
            delay(2000)
            var result = repository.getBankInformation()
            when (result) {
                is CustomResult.Success -> loadBankInfoSuccess(result.data)
                else                    -> postScreenState(ScreenState.Render(MainState.LoadError))
            }
        }
    }

    fun loadBankInfoSuccess(bankResponse: BankResponse) {
        bankInfomation.set(bankResponse)
        postScreenState(ScreenState.Render(MainState.LoadDone))
    }
}