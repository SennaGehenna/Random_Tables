package io.github.sennagehenna.randomtables.ui.specific_table

import io.github.sennagehenna.randomtables.ui.base.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpecificTableKey(val tableKey: String) : BaseKey() {

    override fun createFragment() = SpecificTableFragment()
}