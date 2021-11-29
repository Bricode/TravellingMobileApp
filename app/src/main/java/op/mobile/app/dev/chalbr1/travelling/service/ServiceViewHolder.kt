package op.mobile.app.dev.chalbr1.travelling.service

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.chalbr1.travelling.databinding.RecyclerViewCountryItemBinding
import op.mobile.app.dev.chalbr1.travelling.model.Country

/**
 * Service view holder
 *
 * @property binding
 * @constructor Create empty Service view holder
 */
class ServiceViewHolder(private var binding: RecyclerViewCountryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country) {
        binding.country = country
        binding.executePendingBindings()
    }
}