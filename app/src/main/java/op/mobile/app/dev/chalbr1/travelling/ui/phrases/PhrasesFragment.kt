package op.mobile.app.dev.chalbr1.travelling.ui.phrases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentPhrasesBinding
import op.mobile.app.dev.chalbr1.travelling.ui.phrases.PhrasesViewModel
import op.mobile.app.dev.chalbr1.travelling.ui.phrases.PhrasesFragmentArgs

/**
 * Phrases fragment
 *
 * @constructor Create empty Phrases fragment
 */
class PhrasesFragment : Fragment() {
    private lateinit var binding: FragmentPhrasesBinding
    private lateinit var viewModel: PhrasesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_phrases,
            container,
            false
        )
        val viewModelFactory = PhrasesViewModelFactory(
            PhrasesFragmentArgs.fromBundle(requireArguments()).country
        )
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(PhrasesViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            phrasesViewModel = viewModel

            return root
        }
    }
}