package op.mobile.app.dev.chalbr1.travelling.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Translation
 *
 * data class for recieving a translation from the api
 * @property code
 * @property lang
 * @property text
 * @constructor Create empty Translation
 */
@Parcelize
data class Translation (
    val code: String,
    val lang: String,
    val text: List<String>,
) : Parcelable