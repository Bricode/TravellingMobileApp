package op.mobile.app.dev.chalbr1.travelling.ui.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Translation view model factory
 *
 * @property country
 * @constructor Create empty Translation view model factory
 */
class TranslationViewModelFactory(private val country: Country): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslationViewModel::class.java))
            return TranslationViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}