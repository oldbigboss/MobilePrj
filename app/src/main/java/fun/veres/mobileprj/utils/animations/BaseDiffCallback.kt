package `fun`.veres.mobileprj.utils.animations

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<BM : Any> : DiffUtil.Callback() {

    private val oldItems = ArrayList<BM>()
    private val newItems = ArrayList<BM>()

    override fun getNewListSize(): Int = newItems.size

    override fun getOldListSize(): Int = oldItems.size

    internal fun setItems(oldItems: List<BM>, newItems: List<BM>) {
        this.oldItems.clear()
        this.oldItems.addAll(oldItems)
        this.newItems.clear()
        this.newItems.addAll(newItems)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(
            oldItems[oldItemPosition],
            newItems[newItemPosition]
        )
    }

    abstract fun areItemsTheSame(oldItem: BM, newItem: BM): Boolean

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(
            oldItems[oldItemPosition],
            newItems[newItemPosition]
        )
    }

    abstract fun areContentsTheSame(oldItem: BM, newItem: BM): Boolean
}