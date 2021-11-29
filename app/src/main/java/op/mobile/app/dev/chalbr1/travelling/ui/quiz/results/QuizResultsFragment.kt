package op.mobile.app.dev.chalbr1.travelling.ui.quiz.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentQuizResultsBinding
import op.mobile.app.dev.chalbr1.travelling.result.ResultAdapter
import op.mobile.app.dev.chalbr1.travelling.result.ResultApplication
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * Quiz results fragment
 *
 * @constructor Create empty Quiz results fragment
 */
class QuizResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentQuizResultsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_results,
            container,
            false
        )

        val bundle = QuizResultsFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory =
            QuizResultsViewModelFactory((activity?.applicationContext as ResultApplication).repository)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(QuizResultsViewModel::class.java)
        /**
         * inserts the users score into the database
         */
        viewModel.insertResultsDetail(Result(bundle.countryName, bundle.score))

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizResultsViewModel = viewModel
            rvResultDetails.adapter = ResultAdapter()
            return root
        }
    }
}
