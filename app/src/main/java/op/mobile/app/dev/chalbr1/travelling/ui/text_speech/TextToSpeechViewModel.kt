package op.mobile.app.dev.chalbr1.travelling.ui.text_speech


import androidx.lifecycle.ViewModel
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Text to speech view model
 *
 * @constructor
 *
 * @param _country
 */
class TextToSpeechViewModel (_country: Country): ViewModel() {
    var country: Country = _country
}