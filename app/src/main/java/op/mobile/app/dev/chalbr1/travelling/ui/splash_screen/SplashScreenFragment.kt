package op.mobile.app.dev.chalbr1.travelling.ui.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentSplashScreenBinding

/**
 * Splash screen fragment
 *
 * @constructor Create empty Splash screen fragment
 */
class SplashScreenFragment : Fragment() {
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
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentSplashScreenBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash_screen,
            container,
            false
        )

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val action =
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
                view?.findNavController()?.navigate(action)
            },
            3000
        )
        return binding.root
    }
}