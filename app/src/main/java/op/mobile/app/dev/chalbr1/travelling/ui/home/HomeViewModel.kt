package op.mobile.app.dev.chalbr1.travelling.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.chalbr1.travelling.model.Country
import op.mobile.app.dev.chalbr1.travelling.api.ServiceStatus
import op.mobile.app.dev.chalbr1.travelling.api.RetrofitInstance

/**
 * Home view model
 *
 * @constructor Create empty Home view model
 */
class HomeViewModel : ViewModel() {
    /**
     * Gist to get country data
     */
    private val baseUrl =
        "https://gist.githubusercontent.com/Bricode/861979e91b4c2ef909c078370a9516da/"

    /**
     * status object
     */
    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    /**
     * _response object
     */
    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    /**
     * calls the retrofit instance to get data from the gist
     */
    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitCountryService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _response.value = ArrayList()
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}