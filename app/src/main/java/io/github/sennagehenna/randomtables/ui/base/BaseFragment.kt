package io.github.sennagehenna.randomtables.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhuinden.simplestack.Backstack
import io.github.sennagehenna.randomtables.MainActivity

abstract class BaseFragment : Fragment() {


    abstract val layoutId: Int

    open val bottomFabAction: BottomFabAction? = null

    val backStack: Backstack
        get() = (requireActivity() as MainActivity).backStack

    fun <Key : BaseKey> getKey(): Key = arguments?.getParcelable<Key>(BaseKey.KEY)!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(layoutId, container, false)
        setBottomFabAction(bottomFabAction)
        return inflate
    }


    class BottomFabAction(val drawableRes: Int, val click: Click)

    private fun setBottomFabAction(bottomFabAction: BottomFabAction?) {
        (activity as MainActivity?)?.apply {
            if (bottomFabAction != null) {
                fab.setImageDrawable(getDrawable(bottomFabAction.drawableRes))
                fab.setOnClickListener {
                    bottomFabAction.click()
                }
                fab.visibility = View.VISIBLE
            } else {
                fab.setImageDrawable(null)
                fab.setOnClickListener(null)
                fab.visibility = View.GONE
            }

        }
    }
}