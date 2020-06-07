package io.github.sennagehenna.randomtables.ui.start

import io.github.sennagehenna.randomtables.ui.base.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StartKey(private val ignored: String = StartKey::class.java.simpleName) : BaseKey() {

    override fun createFragment() = StartFragment()
}