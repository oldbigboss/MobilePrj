package `fun`.veres.mobileprj.ui.base

import `fun`.veres.mobileprj.utils.navigation.NavigationController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes val layoutId: Int) : AppCompatActivity() {

    open val fragmentContainerId: Int
        get() = 0

    val navigationController = NavigationController.getInstance()

    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        navigationController.setActivity(this)
        super.onCreate(savedInstanceState)
        root = LayoutInflater.from(this).inflate(layoutId, null)
        setContentView(root)
    }

    override fun onStart() {
        navigationController.setActivity(this)
        super.onStart()
    }

    override fun onStop() {
        navigationController.removeActivity(this)
        super.onStop()
    }

}