package com.android.trustingsocial.test.util.inputvalidator

interface LoanInputValidator {
    fun validatePhone(phone : String) : Boolean
    fun validateNationalId(id : String) : Boolean
}