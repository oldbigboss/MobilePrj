package `fun`.veres.mobileprj.utils.navigation

import `fun`.veres.mobileprj.ui.base.BaseActivity
import `fun`.veres.mobileprj.ui.base.BaseFragment
import `fun`.veres.mobileprj.utils.animations.TransactionAnimation
import `fun`.veres.mobileprj.utils.animations.applyAnimation
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

abstract class BaseNavigationController {
    private var currentActivity = WeakReference<BaseActivity?>(null)

    fun setActivity(activity: BaseActivity?) {
        currentActivity = WeakReference(activity)
    }

    fun removeActivity(baseActivity: BaseActivity) {
        if (currentActivity.get() == baseActivity) currentActivity.clear()
    }

    protected fun addFragment(
        fragment: BaseFragment?,
        addToBackStack: Boolean = true,
        animation: TransactionAnimation? = null
    ) {
        if (fragment != null)
            currentActivity.get()?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .applyAnimation(animation)
                    .replace(it.fragmentContainerId, fragment, fragment::class.java.simpleName)
                    .also { transaction -> if (addToBackStack) transaction.addToBackStack(fragment::class.java.simpleName) }
                    .commitAllowingStateLoss()
            }
    }

    fun onBackPressed() {
        currentActivity.get()?.let {
            if (it.supportFragmentManager.backStackEntryCount == 0) it.finish()
            else it.supportFragmentManager.popBackStack()
        }
    }

    fun newFlow(fragment: BaseFragment?, animation: TransactionAnimation? = null) {
        if (fragment != null)
            currentActivity.get()?.let {
                it.supportFragmentManager.popBackStack(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                it.supportFragmentManager
                    .beginTransaction()
                    .applyAnimation(animation)
                    .replace(it.fragmentContainerId, fragment)
                    .commitAllowingStateLoss()
            }
    }

    fun makeToast(@StringRes message: Int) {
        currentActivity.get()?.let {
            Toast.makeText(it, it.getText(message), Toast.LENGTH_LONG).show()
        }
    }

}