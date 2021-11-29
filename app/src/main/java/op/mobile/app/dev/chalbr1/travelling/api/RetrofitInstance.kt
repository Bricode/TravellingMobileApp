package op.mobile.app.dev.chalbr1.travelling.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance
 *
 * This class is a generic retrofitBuilder, that takes in a url and accesses an api at that url
 * @property url
 * @constructor Create empty Retrofit instance
 */
class RetrofitInstance(private val url: String) {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitCountryService: ICountry by lazy {
        retrofit.create(ICountry::class.java)
    }

    val retrofitTranslationService: ITranslation by lazy {
        retrofit.create(ITranslation::class.java)
    }
}