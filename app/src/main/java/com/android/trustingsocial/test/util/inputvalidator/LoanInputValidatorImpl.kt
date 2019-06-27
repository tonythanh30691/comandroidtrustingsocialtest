package com.android.trustingsocial.test.util.inputvalidator

class LoanInputValidatorImpl : LoanInputValidator {

    companion object {
        private const val PHONE_POSFIX_LENGHT = 7
        private val PHONE_PREFIX = arrayListOf(
            "0120",
            "0121", "0122", "0123", "0124", "0125", "0126", "0127", "0128", "0129",
            "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0186",
            "0188", "0199", "086", "088", "089", "090", "091", "092", "093", "094",
            "095", "096", "097", "098", "099"
        ).joinToString("|", prefix = "(", postfix = ")")
        val PHONE_REGEX_PATTERN = "^($PHONE_PREFIX)\\d{$PHONE_POSFIX_LENGHT}$"

        private const val NATIONAL_ID_MIN_LENGTH = 9
        private const val NATIONAL_ID_MAX_LENGTH = 12
        private const val NATIONAL_ID_REGEX_PATTERN = "^d{$NATIONAL_ID_MIN_LENGTH, $NATIONAL_ID_MAX_LENGTH}$"
    }

    override fun validateNationalId(id : String) : Boolean {
        if (id.isNotEmpty()) {
            return NATIONAL_ID_REGEX_PATTERN.toRegex().matches(id)
        }
        return true
    }

    override fun validatePhone(phone : String) : Boolean {
        return PHONE_REGEX_PATTERN.toRegex().matches(phone.trim())
    }

}