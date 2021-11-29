package op.mobile.app.dev.chalbr1.travelling.model

/**
 * Quiz
 *
 * defines what properties the quiz class has
 * @property question
 * @property answers
 * @property img
 * @constructor Create empty Quiz
 */
data class Quiz(
    val question: String,
    val answers: List<String>,
    val img: String
)
