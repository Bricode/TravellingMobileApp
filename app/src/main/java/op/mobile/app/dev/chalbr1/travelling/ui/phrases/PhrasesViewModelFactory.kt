package op.mobile.app.dev.chalbr1.travelling.ui.phrases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.model.Country
import op.mobile.app.dev.chalbr1.travelling.ui.phrases.PhrasesViewModel

/**
 * Phrases view model factory
 *
 * @property country
 * @constructor Create empty Phrases view model factory
 */
@Suppress("UNCHECKED_CAST")
class PhrasesViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhrasesViewModel::class.java))
            return PhrasesViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}