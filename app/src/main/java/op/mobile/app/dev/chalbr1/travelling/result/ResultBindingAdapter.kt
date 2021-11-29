package op.mobile.app.dev.chalbr1.travelling.result

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * Bind recycler view
 *
 * @param recyclerView
 * @param data
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Result>?) {
    val adapter = recyclerView.adapter as ResultAdapter
    adapter.submitList(data)
}