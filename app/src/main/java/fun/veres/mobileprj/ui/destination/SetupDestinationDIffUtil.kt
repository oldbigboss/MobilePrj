package `fun`.veres.mobileprj.ui.destination

import `fun`.veres.mobileprj.domain.destination.DestinationBusiness
import `fun`.veres.mobileprj.utils.animations.BaseDiffCallback

class SetupDestinationDIffUtil : BaseDiffCallback<DestinationBusiness>() {

    override fun areItemsTheSame(
        oldItem: DestinationBusiness,
        newItem: DestinationBusiness
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: DestinationBusiness,
        newItem: DestinationBusiness
    ): Boolean {
        return true
    }

}