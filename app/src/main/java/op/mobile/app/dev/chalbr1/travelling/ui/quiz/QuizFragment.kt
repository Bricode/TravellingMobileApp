package op.mobile.app.dev.chalbr1.travelling.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentQuizBinding

/**
 * Quiz fragment
 *
 * @constructor Create empty Quiz fragment
 */
class QuizFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel

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
        val binding: FragmentQuizBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz,
            container,
            false
        )

        /**
         * Get the Country object from the bundle. It is passed from the
         * HomeFragment to the QuizFragment when the user clicks on
         * a RecyclerView item
         */
        val viewModelFactory =
            /**
             * QuizViewModelFactory allows you to retrieve the Country object passed
             * from HomeFragment
             */
            QuizViewModelFactory(
                QuizFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(QuizViewModel::class.java)

        /**
         * Observe when the quiz is over
         */
        viewModel.isFinished.observe(
            viewLifecycleOwner,
            Observer {
                if (it) isFinished()
            }
        )

        viewModel.setQuestion()
        viewModel.startTimer()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizViewModel = viewModel

            btnSubmit.setOnClickListener {
                val checkedRadioBtnId = binding.radioBtnGroupAnswers.checkedRadioButtonId

                val correctAnswer = viewModel.quiz.value?.answers?.get(0)

                if (checkedRadioBtnId != -1) {
                    var answerIdx = 0

                    when (checkedRadioBtnId) {
                        R.id.radio_btn_answer_two -> answerIdx = 1
                        R.id.radio_btn_answer_three -> answerIdx = 2
                        R.id.radio_btn_answer_four -> answerIdx = 3
                    }

                    if (viewModel.answers.value?.get(answerIdx)
                        == correctAnswer
                    ) {
                        viewModel.addScore()
                        Toast.makeText(context,"Correct Answer", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,
                            "Incorrect answer, The correct answer is $correctAnswer", Toast.LENGTH_SHORT).show()
                    }

                    viewModel.addQuestionIdx()

                    if (viewModel.questionIdx.value!! < viewModel.country.quiz.size) {
                        viewModel.setQuestion()

                        binding.radioBtnGroupAnswers.clearCheck()
                        binding.invalidateAll()
                    } else {
                        isFinished() // Quiz is finished
                    }
                } else {
                    Toast.makeText(context,"No Answer Selected", Toast.LENGTH_SHORT).show()
                }
            }
            return root
        }
    }

    /**
     * Is finished checks if the quiz has ended and directs to the quiz results fragment     *
     */
    private fun isFinished() {
        val action = QuizFragmentDirections
            .actionQuizFragmentToQuizResultsFragment(
                viewModel.score.value!!,
                viewModel.country.name
            )
        findNavController().navigate(action)
    }
}
