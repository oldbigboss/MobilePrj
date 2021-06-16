package `fun`.veres.mobileprj.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.internal.managers.ViewComponentManager

abstract class BaseRvAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {

    var viewmodel: BaseViewModel? = null

    open var items = mutableListOf<T>()

    open fun updateData(list: List<T>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    abstract fun getItemResId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        DataBindingViewHolder<T>(
            parent.context, DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getItemResId(viewType), parent, false
            ), viewmodel
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is DataBindingViewHolder<*>) {
            (holder as DataBindingViewHolder<T>).bind(items[position])
        }
    }
}

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

class DataBindingViewHolder<T>(
    private val context: Context,
    var binding: ViewDataBinding?,
    var viewmodel: Any?
) : BaseViewHolder(binding?.root!!) {
    fun bind(item: T) {
        binding?.apply {
            setVariable(BR.item, item)
            setVariable(BR.viewmodel, viewmodel)
            executePendingBindings()
            if (context is BaseActivity)
                lifecycleOwner = context
            if (context is Fragment)
                lifecycleOwner = (context as Fragment)
            if (context is ViewComponentManager.FragmentContextWrapper)
                lifecycleOwner = context.fragment.viewLifecycleOwner

        }
    }
}