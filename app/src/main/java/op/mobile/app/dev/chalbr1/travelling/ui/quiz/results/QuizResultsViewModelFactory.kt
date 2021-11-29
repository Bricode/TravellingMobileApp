package op.mobile.app.dev.chalbr1.travelling.ui.quiz.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.repository.ResultRepository

/**
 * Quiz results view model factory
 *
 * checks that the quiz view model is valid
 * @property repository
 * @constructor Create empty Quiz results view model factory
 */
@Suppress("UNCHECKED_CAST")
class QuizResultsViewModelFactory(
    private val repository: ResultRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizResultsViewModel::class.java))
            return QuizResultsViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
