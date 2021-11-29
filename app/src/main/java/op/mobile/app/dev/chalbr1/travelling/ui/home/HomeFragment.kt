package op.mobile.app.dev.chalbr1.travelling.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.databinding.FragmentHomeBinding
import op.mobile.app.dev.chalbr1.travelling.helpers.IOnClickListener
import op.mobile.app.dev.chalbr1.travelling.helpers.recyclerview.CountryRVAdapter

/**
 * Home fragment
 * the fragment used to display the countries to select from
 * @constructor Create empty Home fragment
 */
class HomeFragment : Fragment(), IOnClickListener {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val viewModel: HomeViewModel by viewModels()

        /**
         * This disables the back button. You can not go to the
         * previous Fragment
         */
        activity?.onBackPressedDispatcher?.addCallback(this) {}

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = viewModel

            rvCountries.adapter = CountryRVAdapter(this@HomeFragment)

            return root
        }
    }

    /**
     * On item click
     * takes a position
     * @param position
     */
    override fun onItemClick(position: Int) {
        val item = binding.homeViewModel!!.response.value!![position]
        val action =
            HomeFragmentDirections.actionHomeFragmentToCountryOptionsFragment(item)
        findNavController().navigate(action)
    }
}