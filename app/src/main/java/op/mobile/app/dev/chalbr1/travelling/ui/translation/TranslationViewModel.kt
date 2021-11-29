package op.mobile.app.dev.chalbr1.travelling.ui.translation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.chalbr1.travelling.api.RetrofitInstance
import op.mobile.app.dev.chalbr1.travelling.api.ServiceStatus
import op.mobile.app.dev.chalbr1.travelling.model.Country
import op.mobile.app.dev.chalbr1.travelling.model.Translation
import java.lang.Exception

/**
 * Translation view model
 *
 * calls the translation api
 * @constructor
 *
 * @param _country
 */
class TranslationViewModel(_country: Country): ViewModel() {
    var country: Country = _country

    private val baseUrl =
        "https://translate.yandex.net/api/v1.5/tr.json/"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<Translation>()
    val response: LiveData<Translation> get() = _response

    /**
     * Get response
     *
     * @param text
     */
    fun getResponse(text: String?) {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslationService.getResponse(
                    "trnsl.1.1.20200329T025311Z.37f6897b8a99dbd9.bb42d876c007fde0812c365015625fde8c0f0163",text!!,"en-${country.langCode}"
                )
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}