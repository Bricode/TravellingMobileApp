package op.mobile.app.dev.chalbr1.travelling.helpers.settings

import android.content.Context
import androidx.appcompat.widget.SwitchCompat
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import androidx.appcompat.app.AppCompatDelegate

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Settings manager
 *
 * This is responsible for the light mode dark mode in the app
 * It stores which was previously active when the app was shut as well
 * @constructor
 *
 * @param context
 */
class SettingsManager(context: Context) {
    /**
     * makes a local datastore for storing which state is active
     */
    private val dataStore: DataStore<Preferences> =
        context.applicationContext.createDataStore(DATA_STORE_NAME)

    val uiModeFlow: Flow<UIMode> = dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        when (it[IS_DARK_MODE] ?: false) {
            true -> UIMode.DARK
            false -> UIMode.LIGHT
        }
    }

    suspend fun setUIMode(uiMode: UIMode) {
        dataStore.edit {
            it[IS_DARK_MODE] = when (uiMode) {
                UIMode.LIGHT -> false
                UIMode.DARK -> true
            }
        }
    }

    private fun uiModeFeatures(
        mode: Int,
        isMainActivity: Boolean,
        swUIMode: SwitchCompat?,
        uiModeChecked: Boolean
    ) {
        AppCompatDelegate.setDefaultNightMode(mode)
        swUIMode?.isChecked = uiModeChecked
    }

    fun setCheckedUiMode(uiUIMode: UIMode?, isMainActivity: Boolean, swUiMode: SwitchCompat?) {
        when (uiUIMode) {
            UIMode.LIGHT -> {
                uiModeFeatures(AppCompatDelegate.MODE_NIGHT_NO, isMainActivity, swUiMode, false)
            }
            UIMode.DARK -> {
                uiModeFeatures(AppCompatDelegate.MODE_NIGHT_YES, isMainActivity, swUiMode, true)
            }
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "settings.pref"
        private val IS_DARK_MODE = preferencesKey<Boolean>("is_dark_mode")
    }
}