package io.github.sennagehenna.randomtables.ui.start

import io.github.sennagehenna.randomtables.ui.base.BaseFragment
import io.github.sennagehenna.randomtables.ui.base.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyTablesKey(private val ignored:String = "StartKey"): BaseKey()  {
    override fun createFragment(): BaseFragment  = MyTablesFragment()
}