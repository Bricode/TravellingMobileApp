package op.mobile.app.dev.chalbr1.travelling.ui.text_speech

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.model.Country
import op.mobile.app.dev.chalbr1.travelling.ui.text_speech.TextToSpeechViewModel

/**
 * Text to speech view model factory
 *
 * @property country
 * @constructor Create empty Text to speech view model factory
 */
class TextToSpeechViewModelFactory(private val country: Country): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TextToSpeechViewModel::class.java))
            return TextToSpeechViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}