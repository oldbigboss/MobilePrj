package `fun`.veres.mobileprj.ui.single

import `fun`.veres.mobileprj.R
import `fun`.veres.mobileprj.ui.base.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SingleActivity : BaseActivity(R.layout.activity_single) {

    override val fragmentContainerId: Int = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationController.openSetupDestinationFragment()
    }
}