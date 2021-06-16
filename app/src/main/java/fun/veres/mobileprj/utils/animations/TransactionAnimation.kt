package `fun`.veres.mobileprj.utils.animations

import `fun`.veres.mobileprj.R
import androidx.fragment.app.FragmentTransaction

sealed class TransactionAnimation

class FragmentTransition(
    val transition: Int
) : TransactionAnimation()

data class Custom(
    val enter: Int,
    val exit: Int,
    val popEnter: Int,
    val popExit: Int
) : TransactionAnimation()

object CustomTransactions {

    fun slideFromLeft() = Custom(
        R.anim.slide_from_left,
        R.anim.fade_out_short,
        0,
        R.anim.slide_to_left
    )

}

object StandardTransition {
    fun open() = FragmentTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
}
