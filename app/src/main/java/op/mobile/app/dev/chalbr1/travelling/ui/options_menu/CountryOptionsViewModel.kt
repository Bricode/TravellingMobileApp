package op.mobile.app.dev.chalbr1.travelling.ui.options_menu


import androidx.lifecycle.ViewModel
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Country options view model
 *
 * @constructor
 *
 * @param _country
 */
class CountryOptionsViewModel(_country: Country) : ViewModel() {
    var country: Country = _country
}