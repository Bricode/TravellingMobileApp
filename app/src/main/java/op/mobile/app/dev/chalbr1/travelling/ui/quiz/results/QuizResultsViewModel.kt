package op.mobile.app.dev.chalbr1.travelling.ui.quiz.results

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import op.mobile.app.dev.chalbr1.travelling.model.Result
import op.mobile.app.dev.chalbr1.travelling.repository.ResultRepository

/**
 * Quiz results view model
 *
 * @property repository
 * @constructor Create empty Quiz results view model
 */
class QuizResultsViewModel(private val repository: ResultRepository) : ViewModel() {
    /**
     * declares all scores val as live data result
     */
    val allScores: LiveData<List<Result>> = repository.allScores.asLiveData()

    /**
     * Insert results detail
     *
     * @param result
     */
    fun insertResultsDetail(result: Result) = viewModelScope.launch {
        repository.insertResultDetail(result)
    }
}
