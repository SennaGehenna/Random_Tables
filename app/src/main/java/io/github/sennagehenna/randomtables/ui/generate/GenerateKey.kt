package io.github.sennagehenna.randomtables.ui.generate

import io.github.sennagehenna.randomtables.ui.base.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenerateKey(
    private val ignored: String = GenerateKey::class.java.simpleName
) : BaseKey() {

    override fun createFragment() = GenerateFragment()
}
