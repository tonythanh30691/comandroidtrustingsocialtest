package com.android.trustingsocial.test.ui.customview

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.util.inputvalidator.LoanInputValidatorImpl

class LoanNumberEditText @JvmOverloads constructor(context: Context,
                                                            attributeSet: AttributeSet? = null,
                                                            defStyleAttr : Int = android.R.attr.editTextStyle) : EditText(context, attributeSet, defStyleAttr) {

    companion object {
        const val GENERIC_TYPE = 0
        const val PHONE_INPUT_TYPE = 1
        const val ID_INPUT_TYPE = 2

        const val PHONE_MAX_LENGTH = 11
        const val ID_MAX_LENGTH = 12
    }

    private val loanInputValidator : LoanInputValidatorImpl = LoanInputValidatorImpl()
    private var numberInputType = GENERIC_TYPE
    private var errorText = ""

    init {
        parseAttr(attributeSet)
        initStyle()
    }

    private fun initStyle() {
        setSingleLine()
        maxLines = 1
        if (numberInputType != GENERIC_TYPE) {
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(getMaxLength()))
        }
    }

    private fun parseAttr(attributeSet: AttributeSet?) {
        attributeSet.let {
            var attrs = context.obtainStyledAttributes(it, R.styleable.LoanNumberEditText, 0, 0)
            numberInputType = attrs.getInt(R.styleable.LoanNumberEditText_numberType, GENERIC_TYPE)
            errorText = attrs.getString(R.styleable.LoanNumberEditText_errorText) ?: getErrorStringFromType()
            attrs.recycle()
        }
    }

    private fun getMaxLength() : Int {
        return when(numberInputType) {
            PHONE_INPUT_TYPE         -> PHONE_MAX_LENGTH
            else                     -> ID_MAX_LENGTH
        }
    }

    private fun getErrorStringFromType() : String {
        return when(numberInputType) {
            PHONE_INPUT_TYPE         ->  context.getString(R.string.loan_edit_text_default_phone_error)
            ID_INPUT_TYPE            ->  context.getString(R.string.loan_edit_text_default_id_error)
            else                     ->  context.getString(R.string.loan_edit_text_default_generic_error)
        }
    }

    fun isInputValid() : Boolean {
        var textContent = text.toString()
        var isValid = when(numberInputType) {
            PHONE_INPUT_TYPE        ->  loanInputValidator.validatePhone(textContent)
            ID_INPUT_TYPE           ->  loanInputValidator.validateNationalId(textContent)
            else                    ->  textContent.isNotEmpty()
        }
        if (!isValid) {
            error = errorText
            requestFocus()
        }
        return isValid
    }

}