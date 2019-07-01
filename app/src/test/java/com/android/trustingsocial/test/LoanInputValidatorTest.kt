package com.android.trustingsocial.test

import com.android.trustingsocial.test.util.inputvalidator.LoanInputValidatorImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoanInputValidatorTest {

    @InjectMocks lateinit var loanInputValidator : LoanInputValidatorImpl

    companion object {
        const val PHONE_NUMBER_EMPTY_INPUT = ""
        const val PHONE_NUMBER_CORRECT_TEN_DIGIT = "0913233456"
        const val PHONE_NUMBER_CORRECT_ELEVEN_DIGIT = "01213456789"

        const val PHONE_NUMBER_NINE_DIGIT_CORRECT_PREFIX = "091323345"
        const val PHONE_NUMBER_TWELVE_DIGIT_CORRECT_PREFIX = "012134567895"
        const val PHONE_NUMBER_TEN_DIGIT_INVALID_PREFIX = "9913456432"
        const val PHONE_NUMBER_TWELVE_DIGIT_INVALID_PREFIX = "01113456789"
        const val PHONE_NUMBER_TEN_DIGIT_INCLUDE_CHARACTER = "09a3233456"

        const val NATIONAL_ID_EMPTY_INPUT = ""
        const val NATIONAL_ID_CORRECT_NINE_DIGIT = "343567543"
        const val NATIONAL_ID_CORRECT_TWELVE_DIGIT = "343567543890"

        const val NATIONAL_ID_INVALID_EIGHT_DIGIT = "34356754"
        const val NATIONAL_ID_INVALID_THIRTEEN_DIGIT = "3435675477890"
        const val NATIONAL_ID_CORRECT_NINE_DIGIT_INCLUDE_CHAR = "34a567543"
    }

    @Test
    fun inputValidator_PhoneNumberTenDigit_returnTrue() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_CORRECT_TEN_DIGIT)).isTrue()
    }

    @Test
    fun inputValidator_PhoneNumberElevenDigit_returnTrue() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_CORRECT_ELEVEN_DIGIT)).isTrue()
    }

    @Test
    fun inputValidator_PhoneNumberEmptyInput_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_EMPTY_INPUT)).isFalse()
    }

    @Test
    fun inputValidator_PhoneNumberNineDigit_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_NINE_DIGIT_CORRECT_PREFIX)).isFalse()
    }

    @Test
    fun inputValidator_PhoneNumberTwelveDigit_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_TWELVE_DIGIT_CORRECT_PREFIX)).isFalse()
    }

    @Test
    fun inputValidator_PhoneNumberTenDigitInvalidPrefix_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_TEN_DIGIT_INVALID_PREFIX)).isFalse()
    }

    @Test
    fun inputValidator_PhoneNumberTwelveDigitInvalidPrefix_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_TWELVE_DIGIT_INVALID_PREFIX)).isFalse()
    }

    @Test
    fun inputValidator_PhoneNumberTenDigitIncludeChar_returnFalse() {
        assertThat(loanInputValidator.validatePhone(PHONE_NUMBER_TEN_DIGIT_INCLUDE_CHARACTER)).isFalse()
    }

    @Test
    fun inputValidator_NationalIDEmptyInput_returnTrue() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_EMPTY_INPUT)).isTrue()
    }

    @Test
    fun inputValidator_NationalIDNineDigit_returnTrue() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_CORRECT_NINE_DIGIT)).isTrue()
    }

    @Test
    fun inputValidator_NationalIDTwelveDigit_returnTrue() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_CORRECT_TWELVE_DIGIT)).isTrue()
    }

    @Test
    fun inputValidator_NationalIDEightDigit_returnFalse() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_INVALID_EIGHT_DIGIT)).isFalse()
    }

    @Test
    fun inputValidator_NationalIDThirteenDigit_returnFalse() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_INVALID_THIRTEEN_DIGIT)).isFalse()
    }

    @Test
    fun inputValidator_NationalIDNineDigitIncludeChar_returnFalse() {
        assertThat(loanInputValidator.validateNationalId(NATIONAL_ID_CORRECT_NINE_DIGIT_INCLUDE_CHAR)).isFalse()
    }

}