package op.mobile.app.dev.chalbr1.travelling.ui.translation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentTranslationBinding

/**
 * Translation fragment
 *
 * @constructor Create empty Translation fragment
 */
class TranslationFragment : Fragment() {
    private lateinit var viewModel: TranslationViewModel
    private lateinit var binding: FragmentTranslationBinding

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
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_translation,
            container,
            false
        )

        val viewModelFactory =
            TranslationViewModelFactory(
                TranslationFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TranslationViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            translationViewModel = viewModel
            translationButton.setOnClickListener {
                viewModel.getResponse(inputTextTranslation.text.toString())
            }
            return root
        }
    }
}