package io.github.sennagehenna.randomtables

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import io.github.sennagehenna.randomtables.ui.base.BaseFragment
import io.github.sennagehenna.randomtables.ui.base.BaseKey
import io.github.sennagehenna.randomtables.ui.base.FragmentStateChanger
import io.github.sennagehenna.randomtables.ui.start.StartKey

class MainActivity : AppCompatActivity(), SimpleStateChanger.NavigationHandler {

    private lateinit var fragmentStateChanger: FragmentStateChanger
    lateinit var backStack: Backstack

    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)


        fragmentStateChanger = FragmentStateChanger(supportFragmentManager, R.id.fragmentContainer)
        backStack = Navigator.configure()
            .setStateChanger(SimpleStateChanger(this))
            .install(this, findViewById(R.id.fragmentContainer), History.single(StartKey()))

    }

    override fun onBackPressed() {
        if (!backStack.goBack()) {
            val fragmentTag = backStack.top<BaseKey>().fragmentTag
            val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
            if (fragment != null && fragment is BaseFragment) {
                fragment.handleBackPress()
            } else {
                goBack()
            }
        }
    }

    fun goBack() {
        super.onBackPressed()
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        fragmentStateChanger.handleStateChange(stateChange)
    }
}
