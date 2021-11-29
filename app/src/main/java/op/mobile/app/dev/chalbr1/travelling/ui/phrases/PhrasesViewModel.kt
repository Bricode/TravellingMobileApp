package op.mobile.app.dev.chalbr1.travelling.ui.phrases

import androidx.lifecycle.ViewModel
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Phrases view model
 *
 * @constructor
 *
 * @param _country
 */
class PhrasesViewModel(_country: Country) : ViewModel() {
    var country: Country = _country
}