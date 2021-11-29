package op.mobile.app.dev.chalbr1.travelling.ui.options_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Country options view model factory
 *checks the country options view model is valid
 * @property country
 * @constructor Create empty Country options view model factory
 */
class CountryOptionsViewModelFactory(private val country: Country): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryOptionsViewModel::class.java))
            return CountryOptionsViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}