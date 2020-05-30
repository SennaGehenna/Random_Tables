package io.github.sennagehenna.randomtables.ui.generate

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.sennagehenna.randomtables.R
import io.github.sennagehenna.randomtables.databinding.ItemSelectedoptionBinding
import io.github.sennagehenna.randomtables.databinding.ScreenGenerateBinding
import io.github.sennagehenna.randomtables.model.SelectedOption
import io.github.sennagehenna.randomtables.ui.base.BaseAdapter
import io.github.sennagehenna.randomtables.ui.base.BaseFragment

class GenerateFragment : BaseFragment() {

    override val layoutId = R.layout.screen_generate

    private val adapter = object : BaseAdapter<SelectedOption>() {
        override fun getItemLayoutId(viewType: Int): Int {
            return R.layout.item_selectedoption
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            with(ItemSelectedoptionBinding.bind(holder.itemView)) {
                txtMajor.text = items[position].title
                txtOption.text = items[position].optionText
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(ScreenGenerateBinding.bind(view)) {

            listOptions.adapter = adapter
            listOptions.layoutManager = LinearLayoutManager(view.context)

            btnGenerate.setOnClickListener {
                adapter.items = generateNew()
            }
        }
    }
}