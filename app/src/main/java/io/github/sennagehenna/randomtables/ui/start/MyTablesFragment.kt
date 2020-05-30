package io.github.sennagehenna.randomtables.ui.start

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.sennagehenna.randomtables.R
import io.github.sennagehenna.randomtables.databinding.ItemMytableBinding
import io.github.sennagehenna.randomtables.databinding.ScreenMytablesBinding
import io.github.sennagehenna.randomtables.model.Option
import io.github.sennagehenna.randomtables.model.ReRollMode
import io.github.sennagehenna.randomtables.model.Table
import io.github.sennagehenna.randomtables.ui.base.BaseAdapter
import io.github.sennagehenna.randomtables.ui.base.BaseFragment

class MyTablesFragment : BaseFragment() {

    override val layoutId = R.layout.screen_mytables

    override val bottomFabAction: BottomFabAction? = BottomFabAction(R.drawable.ic_baseline_add_24) {
        //TODO
    }

    private val adapter = object : BaseAdapter<Table>() {
        override fun getItemLayoutId(viewType: Int) = R.layout.item_mytable

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            with(ItemMytableBinding.bind(holder.itemView)) {
                val table = items[position]
                txtName.text = table.title
                val (type, count) = when (table) {
                    is Table.BasicTable -> Pair("Basic Table", table.options.size)
                    is Table.TableWithSubTables -> Pair("Table of Tables", table.subTables.flatMap { it.options }.size)
                }

                txtType.text = type
                txtCount.text = "$count options"
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(ScreenMytablesBinding.bind(view)) {
            listTables.layoutManager = LinearLayoutManager(view.context)
            listTables.adapter = adapter
        }

        adapter.items = mutableListOf(
            Table.TableWithSubTables(
                title = "Client/Target",
                subTables = listOf(
                    Table.BasicTable(
                        title = "Civilian",
                        options = mutableListOf(
                            "academic or scholar",
                            "laborer or tradesman",
                            "courier or sailor",
                            "merchant or shopkeeper",
                            "artist or writer",
                            "doctor or alchemist"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Criminal",
                        options = listOf(
                            "drug dealer or supplier",
                            "mercenary or thug",
                            "fence or gambler",
                            "spy or informant",
                            "smuggler or thief",
                            "crime boss"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Political",
                        options = listOf(
                            "noble or official",
                            "banker or captain",
                            "revolutionary or refugee",
                            "clergy or cultist",
                            "magistrate or ward boss"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Strange",
                        options = listOf(
                            Option("ghost of %s", ReRollMode.ReRollTable),
                            Option("occult collector"),
                            Option("vampire or other undead"),
                            Option("demon (disguised)"),
                            Option("possessed or hollow"),
                            Option("whisper or cultist")
                        )
                    )
                )
            ),
            Table.TableWithSubTables(
                title = "Work",
                subTables = listOf(
                    Table.BasicTable(
                        title = "Skullduggery",
                        options = listOf(
                            "stalking or surveillance",
                            "sabotage or arson",
                            "lift or plant",
                            "poison or arrange accident",
                            "burglary or heist",
                            "impersonate or misdirect"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Violence",
                        options = listOf(
                            "assassinate",
                            "disappear or ransom",
                            "terrorize or extort",
                            "destroy or deface",
                            "raid or defend",
                            "rob or strong-arm"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Underworld",
                        options = listOf(
                            "escort or security",
                            "smuggle or courier",
                            "blackmail or discredit",
                            "con or espionage",
                            "locate or hide",
                            "negotiate or threaten"
                        ).map { Option(it) }
                    ),
                    Table.BasicTable(
                        title = "Unnatural",
                        options = listOf(
                            "curse or sanctify",
                            "banish or summon",
                            "extract essense",
                            "place or remove runes",
                            "perform/stop ritual",
                            "hollow or revivify"
                        ).map { Option(it) }
                    )
                )
            ),
            Table.BasicTable(
                title = "Twist/Complication",
                options = listOf(
                    "an element is a cover for heretic spirit cult practices",
                    "an occultist has foreseen this job and warned the parties involved",
                    "rogue spirits possess some/most/all of the people involved",
                    "rogue spirits haunt the location",
                    "the job furthers a demon's secret agenda",
                    "the job furthers a vampire's secret agenda",
                    "an element is a front for a criminal enterprise",
                    "a dangerous gang uses the location",
                    "the job is a trap laid by your enemies",
                    "the job is a test for another job",
                    "the job furthers a merchant lord's secret agenda",
                    "the job furthers a crime boss's secret agenda",
                    "the job requires travel by electro	-rail",
                    "must visit the death-lands to do the job (perhaps to the Lost District, outside the lightning barrier)",
                    "the job requires sea travel",
                    "The location moves around (site changes, it's on a vehicle, etc.)",
                    "the job furthers a revolutionary's secret agenda",
                    "the job furthers a city official's secret agenda"
                ).map { Option(it) }
            ),
            Table.BasicTable(
                title = "Connected to a person",
                options = listOf(
                    "PC Friend",
                    "PC Rival",
                    "PC Vice Purveyor",
                    "CREW Contact",
                    "CITY Doskvol notable",
                    "WEIRD ghost, demon, forgotten god"
                ).map { Option(it) }
            ),
            Table.BasicTable(
                title = "and Factions",
                amountOfRolls = 2,
                options = listOf(
                    "The Unseen",
                    "The Silver Nails",
                    "Lord Scurlock",
                    "The Hive",
                    "The Circle of Flame",
                    "The Crows",
                    "The Lampblacks",
                    "The Red Sashes",
                    "The Dimmer Sisters",
                    "The Grinders",
                    "The Billhooks",
                    "The Wraiths",
                    "The Gray Cloaks",
                    "Ulf Ironborn",
                    "The Fog Hounds",
                    "The Lost",
                    "Council or Foundation",
                    "Ironhook Prison",
                    "Spirit Wardens",
                    "Bluecoats / Inspectors",
                    "Imperial Military",
                    "Ink Rakes",
                    "Sparkwrights",
                    "Cyphers",
                    "A Consulate",
                    "Ministry (Transport, Provisions)",
                    "Leviathan Hunters",
                    "Sailors or Dockers",
                    "Gondoliers or Cabbies",
                    "Rail Jacks or Brigade",
                    "Ecstasy of the Flesh",
                    "The Weeping Lady",
                    "Forgotten Gods",
                    "Path of Echoes or Reconciled",
                    "Skovlander Refugees",
                    "Deathlands Scavengers"
                ).map { Option(it) }
            )
        )
    }
}