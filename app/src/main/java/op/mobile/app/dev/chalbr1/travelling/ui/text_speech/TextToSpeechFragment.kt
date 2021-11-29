package op.mobile.app.dev.chalbr1.travelling.ui.text_speech

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentTextToSpeechBinding
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.Toast
import java.util.*

/**
 * Text to speech fragment
 *
 * @constructor Create empty Text to speech fragment
 */
class TextToSpeechFragment : Fragment(), OnInitListener {
    private lateinit var viewModel: TextToSpeechViewModel
    private lateinit var binding: FragmentTextToSpeechBinding
    var t1: TextToSpeech? = null

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
            R.layout.fragment_text_to_speech,
            container,
            false
        )
        t1 = TextToSpeech(activity, this)
        val viewModelFactory =
            TextToSpeechViewModelFactory(
                TextToSpeechFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TextToSpeechViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            textToSpeechViewModel = viewModel
            textToSpeechButton.setOnClickListener {
                speakOut(inputUserText.text.toString())
            }
            return root
        }
    }

    /**
     * Speak out
     * calls the text to speech google module
     * @param text
     */
    private fun speakOut(text : String) {
        t1!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    /**
     * On init
     *
     * @param status
     */
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = t1!!.setLanguage(Locale.forLanguageTag(viewModel.country.langCode))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context,"Language is missing or is unsupported, defaulting to english",Toast.LENGTH_LONG ).show()
            }
        } else {
            Toast.makeText(context,"No Answer Selected", Toast.LENGTH_SHORT).show()
        }
    }
}