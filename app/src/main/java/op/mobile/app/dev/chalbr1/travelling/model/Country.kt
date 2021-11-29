package op.mobile.app.dev.chalbr1.travelling.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Country
 *
 * stores the variables used within a country
 * @property id
 * @property name
 * @property capital
 * @property flagImg
 * @property langCode
 * @property phrases
 * @property quiz
 * @constructor Create empty Country
 */
@Parcelize
data class  Country(
    val id: Int,
    val name: String,
    val capital: String,
    val flagImg: String,
    val langCode: String,
    val phrases: List<String>,
    val quiz: @RawValue List<Quiz> // Serialize List of Quiz
) : Parcelable