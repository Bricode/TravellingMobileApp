package op.mobile.app.dev.chalbr1.travelling.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.model.Country
import op.mobile.app.dev.chalbr1.travelling.ui.quiz.QuizViewModel

/**
 * Quiz view model factory
 *
 * @property country
 * @constructor Create empty Quiz view model factory
 */
@Suppress("UNCHECKED_CAST")
class QuizViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java))
            return QuizViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
