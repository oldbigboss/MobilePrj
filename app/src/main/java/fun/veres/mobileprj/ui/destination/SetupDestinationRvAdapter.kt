package `fun`.veres.mobileprj.ui.destination

import `fun`.veres.mobileprj.R
import `fun`.veres.mobileprj.domain.destination.DestinationBusiness
import `fun`.veres.mobileprj.ui.base.BaseRvAdapter
import androidx.recyclerview.widget.DiffUtil

class SetupDestinationRvAdapter(
    private val diffUtil: SetupDestinationDIffUtil = SetupDestinationDIffUtil()
) : BaseRvAdapter<DestinationBusiness>() {

    override fun updateData(list: List<DestinationBusiness>) {
        val diff = DiffUtil.calculateDiff(diffUtil.apply {
            setItems(items, list)
        })
        items = list.toMutableList()
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemResId(viewType: Int): Int = R.layout.item_destination

}