package `fun`.veres.mobileprj.ui.destination

import `fun`.veres.mobileprj.R
import `fun`.veres.mobileprj.databinding.FragmentSetupDestinationBinding
import `fun`.veres.mobileprj.ui.base.BaseFragment
import androidx.fragment.app.viewModels

class SetupDestinationFragment : BaseFragment(R.layout.fragment_setup_destination) {

    private val viewModel by viewModels<SetupDestinationViewModel>()
    private val adapter by lazy { SetupDestinationRvAdapter() }

    override fun setup() {
        (binding as FragmentSetupDestinationBinding).apply {
            viewmodel = viewModel
            lifecycleOwner = this@SetupDestinationFragment
            rvAdapter = adapter
        }
    }

}