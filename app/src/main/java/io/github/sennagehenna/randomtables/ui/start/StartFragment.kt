package io.github.sennagehenna.randomtables.ui.start

import android.os.Bundle
import android.view.View
import io.github.sennagehenna.randomtables.R
import io.github.sennagehenna.randomtables.databinding.ScreenStartBinding
import io.github.sennagehenna.randomtables.ui.base.BaseFragment
import io.github.sennagehenna.randomtables.ui.my_tables.MyTablesKey

class StartFragment : BaseFragment() {

    override val layoutId: Int = R.layout.screen_start


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(ScreenStartBinding.bind(view)){
            btnMyTables.setOnClickListener {
                backStack.goTo(MyTablesKey())
            }
            btnMySuites.setOnClickListener {
                TODO("not implemented")
            }
        }
    }
}