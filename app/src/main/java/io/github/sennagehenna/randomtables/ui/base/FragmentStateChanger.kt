package io.github.sennagehenna.randomtables.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhuinden.simplestack.StateChange

class FragmentStateChanger(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    fun handleStateChange(stateChange: StateChange) {
        fragmentManager.executePendingTransactions()

        val fragmentTransaction = fragmentManager.beginTransaction().apply {
//            when (stateChange.direction) {
//                StateChange.FORWARD -> {
//                    setCustomAnimations(
//                        R.anim.enter_animation,
//                        R.anim.exit_animation,
//                        R.anim.pop_enter_animation,
//                        R.anim.pop_exit_animation
//                    )
//                }
//                StateChange.BACKWARD -> {
//                    //this is funky and doesn't work how I thought it would
//                    setCustomAnimations(
//                        R.anim.exit_animation,
//                        R.anim.enter_animation,
//                        R.anim.pop_exit_animation,
//                        R.anim.pop_enter_animation
//                    )
//                }
//            }
            val previousState = stateChange.getPreviousKeys<BaseKey>()
            val newState = stateChange.getNewKeys<BaseKey>()
            for (oldKey in previousState) {
                val fragment = fragmentManager.findFragmentByTag(oldKey.fragmentTag)
                if (fragment != null) {
                    if (!newState.contains(oldKey)) {
                        remove(fragment)
                    } else if (!fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
            for (newKey in newState) {
                var fragment: Fragment? = fragmentManager.findFragmentByTag(newKey.fragmentTag)
                if (newKey == stateChange.topNewKey<Any>()) {
                    if (fragment != null) {
                        if (fragment.isRemoving) { // Fragments are quirky, they die asynchronously. Ignore if they're still there.
                            fragment = newKey.newFragment()
                            replace(containerId, fragment, newKey.fragmentTag)
                        } else if (fragment.isDetached) {
                            attach(fragment)
                        }
                    } else {
                        fragment = newKey.newFragment()
                        add(containerId, fragment, newKey.fragmentTag)
                    }
                } else {
                    if (fragment != null && !fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}