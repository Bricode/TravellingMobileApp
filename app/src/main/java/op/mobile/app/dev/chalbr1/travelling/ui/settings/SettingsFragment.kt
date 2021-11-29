package op.mobile.app.dev.chalbr1.travelling.ui.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.helpers.settings.SettingsManager
import op.mobile.app.dev.chalbr1.travelling.helpers.settings.UIMode

/**
 * Settings fragment
 *
 * @constructor Create empty Settings fragment
 */
class SettingsFragment : Fragment() {
    private lateinit var swToggleDarkMode: SwitchCompat

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        swToggleDarkMode = view.findViewById(R.id.sw_toggle_dark_mode)

        val settingsManager = SettingsManager(requireContext())
        settingsManager.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            settingsManager.setCheckedUiMode(it,false, swToggleDarkMode)
        }

        swToggleDarkMode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                when (isChecked) {
                    true -> settingsManager.setUIMode(UIMode.DARK)
                    false -> settingsManager.setUIMode(UIMode.LIGHT)
                }
            }
        }
        return view
    }
}