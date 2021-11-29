package op.mobile.app.dev.chalbr1.travelling.result

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.chalbr1.travelling.databinding.RecyclerViewResultItemBinding
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * Result view holder
 *
 * @property binding
 * @constructor Create empty Result view holder
 */
class ResultViewHolder(private var binding: RecyclerViewResultItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind
     *
     * @param result
     */
        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }
}