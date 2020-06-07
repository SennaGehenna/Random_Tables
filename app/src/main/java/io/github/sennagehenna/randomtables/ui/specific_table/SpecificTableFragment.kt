package io.github.sennagehenna.randomtables.ui.specific_table

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.sennagehenna.randomtables.R
import io.github.sennagehenna.randomtables.databinding.ItemSpecifictableEntryBinding
import io.github.sennagehenna.randomtables.databinding.ScreenSpecifictableBinding
import io.github.sennagehenna.randomtables.model.Option
import io.github.sennagehenna.randomtables.model.ReRollMode
import io.github.sennagehenna.randomtables.model.Table
import io.github.sennagehenna.randomtables.myTables
import io.github.sennagehenna.randomtables.ui.base.BaseAdapter
import io.github.sennagehenna.randomtables.ui.base.BaseFragment
import io.github.sennagehenna.randomtables.ui.base.TypedClick

class SpecificTableFragment : BaseFragment() {

    override val layoutId: Int = R.layout.screen_specifictable

    private val adapter = EntriesAdapter {
        TODO("open add/edit-dialog for entry")
    }

    private fun getTableFromKey(): Table = myTables.first { it.id == getKey<SpecificTableKey>().tableKey }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(ScreenSpecifictableBinding.bind(view)) {

            val table = getTableFromKey()

            txtTableTitle.setText(table.title)
            lblType.text = when (table) {
                is Table.BasicTable -> "Basic Table"
                is Table.TableWithSubTables -> "Table with Subtables"
            }

            btnAddTableEntry.setOnClickListener {
                TODO("open add/edit-dialog for entry")
            }

            btnHelp.setOnClickListener {
                TODO("open help-dialog")
            }

            listEntries.layoutManager = LinearLayoutManager(view.context)
            listEntries.adapter = adapter

        }
    }

    private class EntriesAdapter(private val click: TypedClick<Option>) : BaseAdapter<Option>() {

        override fun getItemLayoutId(viewType: Int) = R.layout.item_specifictable_entry

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            with(ItemSpecifictableEntryBinding.bind(holder.itemView)) {
                val item = items[position]
                lblEntryTitle.text = item.text
                lblRerollType.text = when (item.reRollMode) {
                    ReRollMode.NoReRoll -> ""
                    ReRollMode.ReRollSubTable -> "Reroll on this table"
                    ReRollMode.ReRollTable -> "Reroll on all subtables"
                }
                root.setOnClickListener {
                    click(items[holder.adapterPosition])
                }
            }
        }

    }
}