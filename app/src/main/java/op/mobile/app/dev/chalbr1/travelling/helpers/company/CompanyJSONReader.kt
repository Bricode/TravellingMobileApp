package op.mobile.app.dev.chalbr1.travelling.helpers.company

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.model.Company
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Company j s o n reader
 *
 * Turns a context object into a json response used in the maps module
 * @property context
 * @constructor Create empty Company j s o n reader
 */
class CompanyJSONReader(private val context: Context) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.data)

    fun read(): List<Company> {
        val itemType = object : TypeToken<List<CompanyJSONResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson<List<CompanyJSONResponse>>(reader, itemType).map {
            it.toCompany()
        }
    }
}