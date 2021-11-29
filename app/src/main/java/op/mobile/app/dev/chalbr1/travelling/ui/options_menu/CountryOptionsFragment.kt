package op.mobile.app.dev.chalbr1.travelling.ui.options_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentCountryOptionsBinding

/**
 * Country options fragment
 *
 * @constructor Create empty Country options fragment
 */
class CountryOptionsFragment : Fragment() {
    private lateinit var viewModel: CountryOptionsViewModel
    private lateinit var binding: FragmentCountryOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_country_options,
            container,
            false
        )

        /**
         * country options view model factory init
         */
        val viewModelFactory =
            CountryOptionsViewModelFactory(
                CountryOptionsFragmentArgs.fromBundle(requireArguments()).country
            )

        /**
         * loading view model provider
         */
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(CountryOptionsViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryOptionViewModel = viewModel
            /**
             * redirects to the various country activitys
             */
            quizButton.setOnClickListener {
                val action =
                    CountryOptionsFragmentDirections.actionOptionsToQuizFragment(viewModel.country)
                findNavController().navigate(action)
            }
            translateButton.setOnClickListener {
                val action =
                    CountryOptionsFragmentDirections.actionOptionsToTranslateFragment(viewModel.country)
                findNavController().navigate(action)
            }
            text2SpeechButton.setOnClickListener {
                val action = CountryOptionsFragmentDirections.actionOptionsToTextToSpeechFragment(viewModel.country)
                findNavController().navigate(action)
            }
            commonPhrasesButton.setOnClickListener {
                val action = CountryOptionsFragmentDirections.actionOptionsToPhrasesFragment(viewModel.country)
                findNavController().navigate(action)
            }
            return root
        }
    }
}