package op.mobile.app.dev.chalbr1.travelling.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import op.mobile.app.dev.chalbr1.travelling.databinding.RecyclerViewResultItemBinding
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * Result adapter
 *
 * @constructor Create empty Result adapter
 */
class ResultAdapter :
    ListAdapter<Result, ResultViewHolder>(DiffCallback) {
    /**
     * Diff callback
     *
     * @constructor Create empty Diff callback
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        /**
         * Are items the same
         *
         * @param oldItem
         * @param newItem
         * @return
         */
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        /**
         * Are contents the same
         *
         * @param oldItem
         * @param newItem
         * @return
         */
        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultViewHolder {
        return ResultViewHolder(
            RecyclerViewResultItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    /**
     * On bind view holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(
        holder: ResultViewHolder,
        position: Int
    ) {
        val resultDetail = getItem(position)
        holder.bind(resultDetail)
    }
}