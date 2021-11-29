package op.mobile.app.dev.chalbr1.travelling.service


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.api.ServiceStatus
import op.mobile.app.dev.chalbr1.travelling.helpers.recyclerview.CountryRVAdapter
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Set country list data
 *
 * @param rv
 * @param data
 */
@BindingAdapter("country_list_data")
fun setCountryListData(rv: RecyclerView, data: List<Country>?) {
    val adapter = rv.adapter as CountryRVAdapter
    adapter.submitList(data)
}

/**
 * defines service_status calls in the layout fragments
 */
@BindingAdapter("service_status")
fun setServiceStatus(tv: TextView, status: ServiceStatus?) {
    when (status) {
        ServiceStatus.LOADING -> {
            tv.visibility = View.VISIBLE
            tv.text = tv.context.getString(R.string.loading)
        }
        ServiceStatus.ERROR -> {
            tv.visibility = View.VISIBLE
            tv.text = tv.context.getString(R.string.connection_error)
        }
        ServiceStatus.COMPLETE -> tv.visibility = View.GONE
    }
}

/**
 * defines what a round image is in the layout fragments
 */
@BindingAdapter("round_image")
fun setRoundImage(iv: ImageView, imageUrl: String) {
    Glide.with(iv.context)
        .load(imageUrl)
        .apply(RequestOptions().circleCrop())
        .into(iv)
}

/**
 * defines what a square image is in the layout fragments
 */
@BindingAdapter("square_image")
fun setSquareImage(iv: ImageView, imageUrl: String) {
    Glide.with(iv.context)
        .load(imageUrl)
        .apply(
            RequestOptions.bitmapTransform(RoundedCorners(8))
        )
        .into(iv)
}
