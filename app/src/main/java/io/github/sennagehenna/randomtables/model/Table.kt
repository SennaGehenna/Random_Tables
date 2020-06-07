package io.github.sennagehenna.randomtables.model

sealed class Table(val id: String, val title: String) {

    abstract fun getRandomOptions(): List<Option>

    protected fun List<Option>.getRandomExcluding(option: Option) = filterNot { it == option }.random()

    class BasicTable(id: String, title: String, val amountOfRolls: Int = 1, val options: List<Option>) : Table(id, title) {

        override fun getRandomOptions(): List<Option> {

            return options.shuffled().take(amountOfRolls).map { option ->
                if (option.reRollMode != ReRollMode.NoReRoll) {
                    options.getRandomExcluding(option)
                } else {
                    option
                }
            }
        }
    }

    class TableWithSubTables(id: String, title: String, val subTables: List<BasicTable>) : Table(id, title) {

        override fun getRandomOptions(): List<Option> {
            val subTable: BasicTable = subTables.random()
            return subTable.options.shuffled().take(subTable.amountOfRolls).map { option ->
                when (option.reRollMode) {
                    ReRollMode.NoReRoll -> option
                    ReRollMode.ReRollSubTable -> subTables.flatMap { it.options }.getRandomExcluding(option)
                    ReRollMode.ReRollTable -> subTable.options.getRandomExcluding(option)
                }
            }
        }
    }

}

sealed class ReRollMode {
    object NoReRoll : ReRollMode()
    object ReRollSubTable : ReRollMode()
    object ReRollTable : ReRollMode()
}

data class Option(val text: String, val reRollMode: ReRollMode = ReRollMode.NoReRoll)

data class SelectedOption(
    val title: String,
    val optionText: String
)